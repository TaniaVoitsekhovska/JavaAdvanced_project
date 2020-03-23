package ua.lviv.home.servlets;

import org.apache.commons.lang3.ObjectUtils;
import ua.lviv.home.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Todo create Session

        if (ObjectUtils.allNotNull(firstName, lastName, email, password)) {
            if (!userService.getByEmail(email).isPresent()){
                userService.insert(email, firstName, lastName, password);
                response.setStatus(HttpServletResponse.SC_CREATED);
                return;
            }
        }
        response.setContentType("text/plain");
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
