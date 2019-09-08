package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.interfaces.ITaskDAO;
import by.gsu.epamlab.model.enums.DataSourceType;

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
