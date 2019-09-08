package by.gsu.epamlab.controllers.interfaces;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActionCommand {
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
