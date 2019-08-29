package by.gsu.epamlab.controllers.user;

import by.gsu.epamlab.interfaces.ActionCommand;
import by.gsu.epamlab.factories.Client;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.controllers.BaseController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ActionUserController",
        urlPatterns = {"/controller"})
public class ActionUserController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        Client client = new Client();

        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);


        if (request.getAttribute(Constants.KEY_ERROR_MESSAGE) == null) {
            redirect(page, request, response);
        } else {
            forward(page, request, response);
        }
    }
}
