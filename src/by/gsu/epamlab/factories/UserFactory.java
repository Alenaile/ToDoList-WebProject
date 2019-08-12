package by.gsu.epamlab.factories;

import by.gsu.epamlab.IUserDAO;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.impl.HardcodedUserImpl;
import by.gsu.epamlab.impl.SqlUserImpl;

public class UserFactory {
    private static IUserDAO userDAO;

    public static void setGlobals(String strDAO) {
        userDAO = Constants.DB_IMPL.equals(strDAO) ? new SqlUserImpl() : new HardcodedUserImpl();
    }

    public static IUserDAO getClassFromFactory() {
        return userDAO;
    }

}
