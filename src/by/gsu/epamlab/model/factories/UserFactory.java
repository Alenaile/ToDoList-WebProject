package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.interfaces.IUserDAO;
import by.gsu.epamlab.model.enums.DataSourceType;

public class UserFactory {

    private static IUserDAO userDAO;

    public static void setGlobals(String strDAO) {
        DataSourceType dataSourceType = DataSourceType.valueOf(strDAO.toUpperCase());
        userDAO = dataSourceType.getUserDAO();
    }

    public static IUserDAO getClassFromFactory() {
        return userDAO;
    }
}
