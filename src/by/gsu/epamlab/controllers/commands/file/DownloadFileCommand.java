package by.gsu.epamlab.controllers.commands.file;

import by.gsu.epamlab.controllers.utils.CommandUtil;
import by.gsu.epamlab.controllers.interfaces.ActionCommand;
import by.gsu.epamlab.model.bean.Task;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class DownloadFileCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(DownloadFileCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String section = request.getParameter(ConstantsJSP.KEY_SECTION);
        int taskId = Integer.parseInt(request.getParameter(ConstantsJSP.KEY_ID));

        HttpSession session = request.getSession();
        User user = CommandUtil.getUser(session);
        ITaskDAO taskDAO = TaskFactory.getClassFromFactory();

        try {
            Task task = CommandUtil.getTaskById(taskId, taskDAO, user);
            taskDAO.downloadFile(task);
        } catch (DAOException e) {
            LOGGER.error(e.toString(), e);
            CommandUtil.setErrorAttribute(request, e);
        }

        return Constants.TASK_PATH + section.toLowerCase() + Constants.EXTENSION;
    }
}
