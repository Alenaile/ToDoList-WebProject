package by.gsu.epamlab.controllers.commands.user;

import by.gsu.epamlab.controllers.interfaces.ActionCommand;
import by.gsu.epamlab.controllers.utils.CommandUtil;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.exceptions.ValidationException;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.factories.UserFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import by.gsu.epamlab.model.interfaces.IUserDAO;
import by.gsu.epamlab.model.utils.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String login = request.getParameter(ConstantsJSP.KEY_LOGIN);
        String password = request.getParameter(ConstantsJSP.KEY_PASSWORD);

        HttpSession session = request.getSession();
        try {
            UserValidator.checkInput(login, password);

            IUserDAO userDAO = UserFactory.getClassFromFactory();
            User user = userDAO.getUser(login, password);
            session.setAttribute(Constants.KEY_USER_ATTR, user);

            ITaskDAO taskDAO = TaskFactory.getClassFromFactory();
            CommandUtil.setAttributeTasks(taskDAO, user, session);

            page = ConstantsJSP.MAIN_PAGE;

        } catch (DAOException | ValidationException e) {
            LOGGER.error(e.toString(), e);
            request.setAttribute(Constants.KEY_ERROR_MESSAGE_ATTR, e.getMessage());
            page = ConstantsJSP.LOGIN_PAGE;
        }
        return page;
    }
}

