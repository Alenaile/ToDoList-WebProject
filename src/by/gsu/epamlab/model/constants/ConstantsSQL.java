package by.gsu.epamlab.model.constants;

public final class ConstantsSQL {
    public static final String SET_USER_SQL = "INSERT INTO `users`(`login`, `password`) VALUES(?,?)";
    public static final String SELECT_USER_WITH_LOGIN = "SELECT * FROM users WHERE login = ?";

    public static final String REMOVE_TASK = "UPDATE `tasks` SET `section` = 'RECYCLEBIN' WHERE `tasks`.`taskId` = ?";
    public static final String FIX_TASK = "UPDATE `tasks` SET `section` = 'FIXED' WHERE `tasks`.`taskId` = ?";
    public static final String RECOVER_TASK = "UPDATE `tasks` SET `section` = 'ACTIVE' WHERE `tasks`.`taskId` = ?";
    public static final String DELETE_TASK = "DELETE  FROM `tasks` WHERE `tasks`.`TaskId` = ?";
    public static final String DELETE_TASKS = "DELETE FROM `tasks` WHERE `tasks`.`section`='RECYCLEBIN' AND `idUser`= ?";
    public static final String GET_TASKS = "SELECT idUser,`date`, `description`, fileName,`section`, taskId FROM tasks WHERE tasks.idUser = ?";  //tasks.date, tasks.description, tasks.file, tasks.section, tasks.taskId
    public static final String ADD_TASK = "INSERT INTO `tasks`(idUser,`date`, `description`, `section`) VALUES(?,?,?,?)";
    public static final String SELECT_FILE_TO_DOWNLOAD = "SELECT  users.tasks.file FROM users.tasks WHERE users.tasks.taskId=?";

    public static final String UPLOAD_FILE = "UPDATE `tasks` SET `fileName`= ? ,`file`= ? WHERE `taskId`= ? ";
    public static final String DELETE_FILE = "UPDATE `tasks` SET `fileName`= NULL ,`file`= NULL WHERE `taskId`= ? ";


    public static final int LOGIN_INDEX = 1;
    public static final int PASSWORD_INDEX = 2;

    public static final int USER_ID_COLUMN_INDEX = 3;


    public static final int ID_USER_COLUMN_IND = 1;
    public static final int DATE_COLUMN_IND = 2;
    public static final int DESCRIPTION_COLUMN_IND = 3;
    public static final int FILE_NAME_COLUMN_IND = 4;
    public static final int SECTION_COLUMN_IND = 5;
    public static final int TASK_ID_COLUMN_IND = 6;


    public static final String ERROR_DOWNLOAD_FILE = "Error. Can't download file";
    public static final String ERROR_UPLOAD_FILE = "Error. Can't upload file";
    public static final String ERROR_DELETE_FILE = "Error. Can't delete file";


    public static final String KEY_FILE = "file";
}
