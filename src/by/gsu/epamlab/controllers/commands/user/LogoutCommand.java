package by.gsu.epamlab.controllers.commands.user;

import by.gsu.epamlab.controllers.interfaces.ActionCommand;
import by.gsu.epamlab.model.constants.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConstantsJSP.WELCOME_PAGE;
        request.getSession().invalidate();
        return page;
    }
}
