package by.gsu.epamlab.constants;

public final class Constants {
    public static final String KEY_USER = "user";
    public static final String WELCOM_PAGE = "/welcome.jsp";
    public static final String LOGIN_PAGE = "/login.jsp";
    public static final String REGISTER_PAGE = "/registrate.jsp";
    public static final String MAIN_PAGE = "/main.jsp";
    public static final String FIXED_PAGE = "jsp/sections/fixed.jsp";
    public static final String RECYCLEBIN_PAGE = "jsp/sections/recyclebin.jsp";
    public static final String TASK_CONTROLLER = "/task";



    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_EMPTY = "";
    public static final String KEY_USER_DAO = "userDao";
    public static final String KEY_TASK_DAO = "taskDao";
    public static final String KEY_TASKS = "tasks";
    public static final String KEY_ACTION = "action";

    public static final String ERROR_LOGIN_NULL = "Login is missing";
    public static final String ERROR_PASSWORD_NULL = "Password is missing";
    public static final String ERROR_LOGIN_EMPTY = "Login is empty";
    public static final String ERROR_PASSWORD_EMPTY = "Password is empty";
    public static final String ERROR_USER_EXISTS = "User with such login already exists";
    public static final String ERROR_PASSWORD = "Wrong password";
    public static final String ERROR_LOGIN = "User doesn't exist or login is wrong";
    public static final String ERROR_SOURCE = "Input source proccessing problems";
    public static final String ERROR_INVALID_PASSWORD ="Invalid password" ;
    public static final String ERROR_INVALID_LOGIN ="Invalid login";
    public static final String ERROR_CANNOT_CREATE_CONN ="Cannot create connection";

    public static final String SET_USER_SQL = "INSERT INTO `users`(`login`, `password`) VALUES(?,?)";
    public static final String SELECT_USER_WITH_LOGIN = "SELECT * FROM users WHERE login = ?";

    public static final String REMOVE_TASK = "UPDATE `tasks` SET `section` = 'RECYCLEBIN' WHERE `tasks`.`taskId` = ?";
    public static final String FIX_TASK = "UPDATE `tasks` SET `section` = 'FIXED' WHERE `tasks`.`taskId` = ?";
    public static final String RECOVER_TASK = "UPDATE `tasks` SET `section` = 'ACTIVE' WHERE `tasks`.`taskId` = ?";
    public static final String DELETE_TASK = "DELETE  FROM `tasks` WHERE `tasks`.`TaskId` = ?";
    public static final String GET_TASKS = "SELECT tasks.date, tasks.description, tasks.file, tasks.section, tasks.taskId FROM tasks WHERE tasks.idUser = ?";
    public static final String ADD_TASK = "INSERT INTO `tasks`(idUser,`date`, `description`, file, `section`) VALUES(?,?,?,?,?)";
    public static final String ERROR_ADD_TASK = "Error caused by server. Can't add data to database";
    public static final String ERROR_GET_TASKS = "Error caused by server. Can't get data from database";
    public static final String ERROR_UPDATE_TASKS = "Error caused by server. Can't update database data";


    public static final int LOGIN_INDEX = 1;
    public static final int PASSWORD_INDEX = 2;
    public static final int ID_INDEX = 3;


    public static final String DB_IMPL = "db";
    public static final String HARDCODED_IMPL = "memory";




    public static final String OPERATION_PARAMETER_NAME = "operation";
    public static final String SECTION_PARAMETER_NAME = "section";
    public static final String CHECK_BOX_NAME = "checkbox";

    public static final String KEY_DATE_PARAMETER = "date";
    public static final String KEY_NEW_TASK_PARAMETER = "newTask";
}
