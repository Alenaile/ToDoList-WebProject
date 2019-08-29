package by.gsu.epamlab.factories;

import by.gsu.epamlab.enums.DataSourceType;
import by.gsu.epamlab.interfaces.ITaskDAO;

public class TaskFactory {

    private static ITaskDAO taskDAO;

    public static void setGlobals(String strDAO) {
        DataSourceType dataSourceType = DataSourceType.valueOf(strDAO.toUpperCase());
        taskDAO = dataSourceType.getTaskDAO();
    }

    public static ITaskDAO getClassFromFactory() {
        return taskDAO;
    }

}
