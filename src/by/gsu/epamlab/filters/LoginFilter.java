/*
package by.gsu.epamlab.filters;


import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsJSP;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter( urlPatterns = { "/accountPages/*" })
public class LoginFilter implements Filter {


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute(ConstantsJSP.KEY_USER);
        if (user == null) {
            session.invalidate();
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(Constants.LOGIN_PAGE);
            return;
        }
        chain.doFilter(request, response);
    }


}

*/
