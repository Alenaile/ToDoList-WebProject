package by.gsu.epamlab.controllers.factories;

import by.gsu.epamlab.controllers.enums.ActionType;
import by.gsu.epamlab.controllers.interfaces.ActionCommand;
import by.gsu.epamlab.model.constants.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    public static ActionCommand defineCommand(HttpServletRequest request) {

        String action = request.getParameter(ConstantsJSP.KEY_ACTION);
        ActionType currentType = ActionType.valueOf(action.toUpperCase());

        ActionCommand current = currentType.getCurrentCommand();
        return current;
    }
}
