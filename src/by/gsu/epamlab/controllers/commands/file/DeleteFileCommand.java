package by.gsu.epamlab.controllers.commands.file;

import by.gsu.epamlab.controllers.interfaces.ActionCommand;
import by.gsu.epamlab.controllers.utils.CommandUtil;
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


public class DeleteFileCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(DeleteFileCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String section = request.getParameter(ConstantsJSP.KEY_SECTION);
        int taskId = Integer.parseInt(request.getParameter(ConstantsJSP.KEY_ID));

        HttpSession session = request.getSession();
        User user = CommandUtil.getUser(session);
        ITaskDAO taskDAO = TaskFactory.getClassFromFactory();

        try {
            Task task = CommandUtil.getTaskById(taskId, taskDAO, user);
            taskDAO.deleteFile(task);
            CommandUtil.updateAttribute(session, taskDAO, user);
        } catch (DAOException e) {
            LOGGER.error(e.toString(), e);
            CommandUtil.setErrorAttribute(request, e);
        }

        return Constants.TASK_PATH + section.toLowerCase() + Constants.EXTENSION;
    }

}
