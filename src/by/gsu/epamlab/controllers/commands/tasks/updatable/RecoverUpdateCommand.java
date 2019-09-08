package by.gsu.epamlab.controllers.commands.tasks.updatable;

import by.gsu.epamlab.controllers.commands.tasks.AbstractUpdateCommand;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.interfaces.ITaskDAO;

public class RecoverUpdateCommand extends AbstractUpdateCommand {

    @Override
    protected void actionProcessing(ITaskDAO taskDAO, User user, String[] checked) throws DAOException {
        taskDAO.recoverTasks(user, checked);
    }
}
