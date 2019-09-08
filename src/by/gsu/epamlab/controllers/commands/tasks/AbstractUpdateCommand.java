package by.gsu.epamlab.controllers.commands.tasks;

import by.gsu.epamlab.controllers.utils.CommandUtil;
import by.gsu.epamlab.controllers.interfaces.ActionCommand;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public abstract class AbstractUpdateCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(AbstractUpdateCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String[] checked = request.getParameterValues(ConstantsJSP.KEY_CHECK_BOX);
        String section = request.getParameter(ConstantsJSP.KEY_SECTION);

        HttpSession session = request.getSession();
        User user = CommandUtil.getUser(session);
        ITaskDAO taskDAO = TaskFactory.getClassFromFactory();

        try {
            actionProcessing(taskDAO, user, checked);
            CommandUtil.updateAttribute(session, taskDAO, user);
        } catch (DAOException e) {
            LOGGER.error(e.toString(), e);
            CommandUtil.setErrorAttribute(request, e);
        }
        return Constants.TASK_PATH + section.toLowerCase() + Constants.EXTENSION;
    }

    protected abstract void actionProcessing(ITaskDAO taskDAO, User user, String[] checked) throws DAOException;

}
