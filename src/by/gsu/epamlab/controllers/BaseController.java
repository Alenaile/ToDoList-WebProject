package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BaseController")
public abstract class BaseController extends HttpServlet {


    protected void forward(String url, HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    protected void forwardError(String message, String url, HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);
        forward(url, request, response);
    }


    protected void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + url);
    }

}
