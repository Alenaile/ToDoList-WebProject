package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.factories.UserFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "StartController",
        urlPatterns = {"/start"},
        initParams = {
                @WebInitParam(name = Constants.KEY_USER_DAO, value = Constants.DB_IMPL),
                @WebInitParam(name = Constants.KEY_TASK_DAO, value = Constants.DB_IMPL)
        }

)
public class StartController extends BaseController {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Logger logger = Logger.getRootLogger();

        String strUserDAO = config.getInitParameter(Constants.KEY_USER_DAO);
        String strTaskDAO = config.getInitParameter(Constants.KEY_TASK_DAO);
        UserFactory.setGlobals(strUserDAO);
        TaskFactory.setGlobals(strTaskDAO);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.forward(ConstantsJSP.WELCOME_PAGE, request, response);
    }


}
