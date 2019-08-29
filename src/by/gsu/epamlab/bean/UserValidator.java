package by.gsu.epamlab.bean;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String LOGIN_PATTERN = "^(?!.*[_.-]{2})[a-zA-Z][a-zA-Z0-9_.-]{3,20}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!_@#$%^&*])[a-zA-Z0-9!_@#$%^&*](?=\\S+$).{6,20}$";


    public static void checkInput(String login, String password) throws ValidationException {
        checkNullOrEmpty(login, Constants.ERROR_LOGIN_NULL, Constants.ERROR_LOGIN_EMPTY);
        checkNullOrEmpty(password, Constants.ERROR_PASSWORD_NULL, Constants.ERROR_PASSWORD_EMPTY);
        validate(login, LOGIN_PATTERN, Constants.ERROR_INVALID_LOGIN);
        validate(password, PASSWORD_PATTERN, Constants.ERROR_INVALID_PASSWORD);

    }


    private static void checkNullOrEmpty(String param, String messageNull, String messageEmpty)
            throws ValidationException {
        if (param == null) {
            throw new ValidationException(messageNull);
        }
        param = param.trim();
        if (Constants.KEY_EMPTY.equals(param)) {
            throw new ValidationException(messageEmpty);
        }
    }


    private static void validate(String param, String paramPattern, String message) throws ValidationException {
        Pattern pattern = Pattern.compile(paramPattern);
        Matcher matcher = pattern.matcher(param);
        if (!matcher.matches()) {
            throw new ValidationException(message);
        }
    }


}
