package by.gsu.epamlab.controllers.user.command;

import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.bean.UserValidator;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.factories.UserFactory;
import by.gsu.epamlab.interfaces.ActionCommand;
import by.gsu.epamlab.interfaces.IUserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(ConstantsJSP.KEY_LOGIN);
        String password = request.getParameter(ConstantsJSP.KEY_PASSWORD);

        try {
            UserValidator.checkInput(login, password);
            IUserDAO userDAO = UserFactory.getClassFromFactory();

            User user = userDAO.getUser(login, password);
            userDAO.addUser(login, password);

            HttpSession session = request.getSession();
            session.setAttribute(Constants.KEY_USER, user);
            page = request.getContextPath() + Constants.MAIN_PAGE;

        } catch (DAOException | ValidationException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            request.setAttribute(Constants.KEY_ERROR_MESSAGE, e.getMessage());
            page = Constants.REGISTER_PAGE;

        }
        return page;
    }
}
