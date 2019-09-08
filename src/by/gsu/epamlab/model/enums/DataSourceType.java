package by.gsu.epamlab.model.enums;

import by.gsu.epamlab.model.interfaces.ITaskDAO;
import by.gsu.epamlab.model.interfaces.IUserDAO;
import by.gsu.epamlab.model.impl.TaskDBImpl;
import by.gsu.epamlab.model.impl.TaskHardcodedImpl;
import by.gsu.epamlab.model.impl.UserDBImpl;
import by.gsu.epamlab.model.impl.UserHardcodedImpl;

public enum DataSourceType {
    MEMORY {
        @Override
        public IUserDAO getUserDAO() {
            return new UserHardcodedImpl();
        }

        @Override
        public ITaskDAO getTaskDAO() {
            return new TaskHardcodedImpl();
        }
    },

    DB {
        @Override
        public IUserDAO getUserDAO() {
            return new UserDBImpl();
        }

        @Override
        public ITaskDAO getTaskDAO() {
            return new TaskDBImpl();
        }
    };

    public abstract IUserDAO getUserDAO();

    public abstract ITaskDAO getTaskDAO();
}
