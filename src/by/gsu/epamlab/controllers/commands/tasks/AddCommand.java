package by.gsu.epamlab.controllers.commands.tasks;

import by.gsu.epamlab.controllers.utils.CommandUtil;
import by.gsu.epamlab.controllers.interfaces.ActionCommand;
import by.gsu.epamlab.model.bean.Task;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AddCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(AddCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String description = request.getParameter(ConstantsJSP.KEY_NEW_TASK);
        String date = request.getParameter(ConstantsJSP.KEY_DATE);
        String section = request.getParameter(ConstantsJSP.KEY_SECTION);

        HttpSession session = request.getSession();
        User user = CommandUtil.getUser(session);

        Task task = new Task(user.getId(), description, date, section);

        ITaskDAO taskDAO = TaskFactory.getClassFromFactory();

        try {
            taskDAO.addTask(task);
            CommandUtil.updateAttribute(session, taskDAO, user);
        } catch (DAOException e) {
            LOGGER.error(e.toString(), e);
            CommandUtil.setErrorAttribute(request, e);
        }
        return ConstantsJSP.MAIN_PAGE;
    }

}
