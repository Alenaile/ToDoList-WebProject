package by.gsu.epamlab.controllers.commands.file;

import by.gsu.epamlab.controllers.interfaces.ActionCommand;
import by.gsu.epamlab.controllers.utils.CommandUtil;
import by.gsu.epamlab.model.bean.Attachment;
import by.gsu.epamlab.model.bean.Task;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class DownloadFileCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(DownloadFileCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String section = request.getParameter(ConstantsJSP.KEY_SECTION);
        int taskId = Integer.parseInt(request.getParameter(ConstantsJSP.KEY_ID));

        HttpSession session = request.getSession();
        User user = CommandUtil.getUser(session);
        ITaskDAO taskDAO = TaskFactory.getClassFromFactory();

        try {
            Task task = CommandUtil.getTaskById(taskId, taskDAO, user);
            Attachment attachment = taskDAO.downloadFile(task);

            String contentType = request.getServletContext().getMimeType(attachment.getName());

            response.setHeader(Constants.CONTENT_TYPE, contentType);

            response.setHeader(Constants.CONTENT_LENGTH, String.valueOf(attachment.getFile().length()));

            response.setHeader(Constants.CONTENT_DISPOSITION, "inline; filename=\"" + attachment.getName() + "\"");


            try (InputStream is = new FileInputStream(attachment.getFile())) {
                byte[] bytes = new byte[1024];
                int bytesRead;

                while ((bytesRead = is.read(bytes)) != -1) {
                    response.getOutputStream().write(bytes, 0, bytesRead);
                }

            } catch (IOException e) {
                LOGGER.error(e.toString(), e);
                throw new DAOException(e.getMessage());
            }

        } catch (DAOException e) {
            LOGGER.error(e.toString(), e);
            CommandUtil.setErrorAttribute(request, e);
        }

        return Constants.TASK_PATH + section.toLowerCase() + Constants.EXTENSION;
    }
}
