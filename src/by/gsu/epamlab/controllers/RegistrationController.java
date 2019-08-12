package by.gsu.epamlab.controllers;

import by.gsu.epamlab.IUserDAO;
import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.factories.UserFactory;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.exceptions.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationController extends BaseController {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String login = request.getParameter(ConstantsJSP.KEY_LOGIN);
        String password = request.getParameter(ConstantsJSP.KEY_PASSWORD);

        try {
            checkInputParam(login, password);
            IUserDAO userDAO = UserFactory.getClassFromFactory();
            userDAO.setUser(login, password);

            User user = new User(login, password);

            HttpSession session = request.getSession();
            session.setAttribute(Constants.KEY_USER, user);
            response.sendRedirect(Constants.JUMP_MAIN);

        } catch (ValidationException | DaoException e) {
            forwardError(e.getMessage(), request, response);
        }
    }
}



