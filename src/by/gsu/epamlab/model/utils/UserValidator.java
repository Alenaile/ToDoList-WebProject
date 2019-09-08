package by.gsu.epamlab.model.utils;

import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UserValidator {

    private static final String LOGIN_PATTERN = "^(?!.*[_.-]{2})[a-zA-Z][a-zA-Z0-9_.-]{3,20}$";
    private static final String PASSWORD_PATTERN = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,15})$";


    public static void checkInput(String login, String password) throws ValidationException {
        validate(login, LOGIN_PATTERN, Constants.ERROR_INVALID_LOGIN);
        validate(password, PASSWORD_PATTERN, Constants.ERROR_INVALID_PASSWORD);

    }


    private static void validate(String param, String paramPattern, String message) throws ValidationException {
        Pattern pattern = Pattern.compile(paramPattern);
        Matcher matcher = pattern.matcher(param);
        if (!matcher.matches()) {
            throw new ValidationException(message);
        }
    }


}
