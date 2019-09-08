package by.gsu.epamlab.controllers.commands.file;

import by.gsu.epamlab.controllers.interfaces.ActionCommand;
import by.gsu.epamlab.controllers.utils.CommandUtil;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;


public class UploadFileCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(UploadFileCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart(ConstantsJSP.KEY_FILE);
        int taskId = Integer.parseInt(request.getParameter(ConstantsJSP.KEY_ID));
        String fileName = getFileName(filePart);

        HttpSession session = request.getSession();
        User user = CommandUtil.getUser(session);
        ITaskDAO taskDAO = TaskFactory.getClassFromFactory();

        try (InputStream is = filePart.getInputStream()) {
            taskDAO.uploadFile(CommandUtil.getTaskById(taskId, taskDAO, user), is, fileName);
            CommandUtil.updateAttribute(session, taskDAO, user);
        } catch (DAOException e) {
            LOGGER.error(e.toString(), e);
            CommandUtil.setErrorAttribute(request, e);
        }

        return ConstantsJSP.MAIN_PAGE;
    }


    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s: items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }

}
