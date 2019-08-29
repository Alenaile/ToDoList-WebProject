package by.gsu.epamlab.controllers.tasks;

import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.controllers.BaseController;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.factories.Client;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.interfaces.ActionCommand;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(
        name = "AddTaskController",
        urlPatterns = {"/task"})
public class ActionTaskController extends BaseController {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);

        try {
            ITaskDAO taskDAO = TaskFactory.getClassFromFactory();
            taskDAO.getTasks(user);

            session.setAttribute(Constants.KEY_TASKS, taskDAO.getTasks(user));

            redirect(Constants.MAIN_PAGE, request, response);
        } catch (DAOException e) {
            forwardError(e.getMessage(), Constants.MAIN_PAGE, request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page;
        Client client = new Client();

        ActionCommand command = client.defineCommand(req);
        page = command.execute(req);


        if (req.getAttribute(Constants.KEY_ERROR_MESSAGE) == null) {
            redirect(page, req, resp);
        } else {
            forward(page, req, resp);
        }

    }
}

