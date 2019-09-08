package by.gsu.epamlab.controllers.commands.tasks;

import by.gsu.epamlab.controllers.interfaces.ActionCommand;
import by.gsu.epamlab.controllers.utils.CommandUtil;
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

public class PermDeleteAllCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(PermDeleteAllCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = CommandUtil.getUser(session);
        ITaskDAO taskDAO = TaskFactory.getClassFromFactory();

        try {
            taskDAO.permanentlyDeleteAllTasks(user);
            session.setAttribute(Constants.KEY_TASKS_ATTR, taskDAO.getTasks(user));
        } catch (DAOException e) {
            LOGGER.error(e.toString(), e);
            CommandUtil.setErrorAttribute(request, e);
        }
        return ConstantsJSP.RECYCLE_BIN_PAGE;
    }

}
