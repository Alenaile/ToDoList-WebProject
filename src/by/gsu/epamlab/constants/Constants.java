package by.gsu.epamlab.constants;

public final class Constants {
    public static final String KEY_USER = "user";
    public static final String JUMP_MAIN = "/main.jsp";
    public static final String JUMP_ERROR = "/index.jsp";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_EMPTY = "";
    public static final String KEY_DAO = "dao";

    public static final String ERROR_NULL = "Login or password is missing";
    public static final String ERROR_EMPTY = "Login or password is empty";
    public static final String ERROR_USER_EXISTS = "User with such login already exists";
    public static final String ERROR_PASSWORD = "Wrong login or password";
    public static final String ERROR_SOURCE = "Input source proccessing problems";

    public static final String SET_USER_SQL = "INSERT INTO `users`(`login`, `password`) VALUES(?,?)";
    public static final String GET_USER_SQL = "SELECT * FROM `users` WHERE  `login` = ? AND `password` = ?";
    public static final int LOGIN_INDEX = 1;
    public static final int PASSWORD_INDEX = 2;

    public static final String DB_IMPL = "jdbc";
}
