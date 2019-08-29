package by.gsu.epamlab.enums;

import by.gsu.epamlab.impl.TaskDBImpl;
import by.gsu.epamlab.impl.TaskHardcodedImpl;
import by.gsu.epamlab.impl.UserDBImpl;
import by.gsu.epamlab.impl.UserHardcodedImpl;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.interfaces.IUserDAO;

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
