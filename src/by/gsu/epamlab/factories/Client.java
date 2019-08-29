package by.gsu.epamlab.factories;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.enums.ActionType;
import by.gsu.epamlab.interfaces.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class Client {

    public ActionCommand defineCommand(HttpServletRequest request) {

        String action = request.getParameter(Constants.KEY_ACTION);
        ActionType currentType = ActionType.valueOf(action.toUpperCase());

        ActionCommand current = currentType.getCurrentCommand();
        return current;
    }
}
