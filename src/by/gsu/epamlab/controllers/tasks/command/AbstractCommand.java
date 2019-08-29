package by.gsu.epamlab.controllers.tasks.command;

import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.interfaces.ActionCommand;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(AbstractCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] checked = request.getParameterValues(Constants.CHECK_BOX_NAME);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);
        try {
            ITaskDAO taskDAO = TaskFactory.getClassFromFactory();

            processChecked(taskDAO, user, checked);

            session.setAttribute(Constants.KEY_TASKS, taskDAO.getTasks(user));
            page = Constants.MAIN_PAGE;

        } catch (DAOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            request.setAttribute(Constants.KEY_ERROR_MESSAGE, e.getMessage());
            page = Constants.MAIN_PAGE;
        }
        return page;
    }

    protected abstract void processChecked(ITaskDAO taskDAO, User user, String[] checked) throws DAOException;

}
