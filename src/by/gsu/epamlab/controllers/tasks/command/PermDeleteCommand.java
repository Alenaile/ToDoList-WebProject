package by.gsu.epamlab.controllers.tasks.command;

import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.ITaskDAO;

public class PermDeleteCommand extends AbstractCommand {

    @Override
    protected void processChecked(ITaskDAO taskDAO, User user, String[] checked) throws DAOException {
        taskDAO.permanentlyDeleteTasks(user, checked);
    }
}
