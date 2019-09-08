package by.gsu.epamlab.controllers.utils;

import by.gsu.epamlab.model.bean.Task;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.interfaces.ITaskDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public final class CommandUtil {

    public static void updateAttribute(HttpSession session, ITaskDAO taskDAO, User user) throws DAOException {
        session.setAttribute(Constants.KEY_TASKS_ATTR, taskDAO.getTasks(user));
    }

    public static void setErrorAttribute(HttpServletRequest request, DAOException e) {
        request.setAttribute(Constants.KEY_ERROR_MESSAGE_ATTR, e.getMessage());
    }

    public static User getUser(HttpSession session) {
        return (User) session.getAttribute(Constants.KEY_USER_ATTR);
    }


    public static Task getTaskById(int taskId, ITaskDAO taskDAO, User user) throws DAOException {
        List<Task> tasks = taskDAO.getTasks(user);
        return tasks.stream()
                .filter(comparableTask -> taskId == comparableTask.getId())
                .findAny()
                .orElse(null);
    }

    public static void setAttributeTasks(ITaskDAO taskDAO, User user, HttpSession session) throws DAOException {
        List<Task> tasks = taskDAO.getTasks(user);
        session.setAttribute(Constants.KEY_TASKS_ATTR, tasks);
    }
}
