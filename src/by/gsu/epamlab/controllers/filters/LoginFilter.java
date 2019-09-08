package by.gsu.epamlab.controllers.filters;

import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.constants.ConstantsJSP;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/tasks/*"})
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER_ATTR);
        if (user == null) {
            session.invalidate();
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(ConstantsJSP.LOGIN_PAGE);
            return;
        }
        chain.doFilter(request, response);
    }


}

