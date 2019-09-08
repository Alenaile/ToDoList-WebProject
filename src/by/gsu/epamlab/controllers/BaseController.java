package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BaseController")
public abstract class BaseController extends HttpServlet {

    void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + url);
    }

    void forward(String url, HttpServletRequest request,
                 HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    boolean isError(HttpServletRequest request) {
        return request.getAttribute(Constants.KEY_ERROR_MESSAGE_ATTR) == null;
    }


}
