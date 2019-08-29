package by.gsu.epamlab.controllers.user.command;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.interfaces.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getContextPath() + Constants.WELCOM_PAGE;
        request.getSession().invalidate();
        return page;
    }
}
