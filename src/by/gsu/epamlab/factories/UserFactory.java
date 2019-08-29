package by.gsu.epamlab.factories;

import by.gsu.epamlab.enums.DataSourceType;
import by.gsu.epamlab.interfaces.IUserDAO;


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
