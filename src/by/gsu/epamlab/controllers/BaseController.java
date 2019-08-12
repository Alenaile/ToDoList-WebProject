package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.factories.UserFactory;
import by.gsu.epamlab.exceptions.ValidationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BaseController")
public class BaseController extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String strDAO = config.getInitParameter(Constants.KEY_DAO);
        UserFactory.setGlobals(strDAO);
    }


    protected void checkInputParam(String login, String password) {
        if (login == null || password == null) {
            throw new ValidationException(Constants.ERROR_NULL);
        }
        login = login.trim();
        password = password.trim();

        if (Constants.KEY_EMPTY.equals(login) || Constants.KEY_EMPTY.equals(password)) {
            throw new ValidationException(Constants.ERROR_EMPTY);
        }
    }

    protected void forward(String url, HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    protected void forwardError(String message, HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);
        forward(Constants.JUMP_ERROR, request, response);
    }
}
