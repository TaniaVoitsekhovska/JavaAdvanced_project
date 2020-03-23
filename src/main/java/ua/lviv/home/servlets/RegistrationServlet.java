package ua.lviv.home.servlets;

import org.apache.commons.lang3.ObjectUtils;
import ua.lviv.home.enteties.User;
import ua.lviv.home.enteties.UserRole;
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
            userService.create(new User(email, firstName, lastName, UserRole.USER.toString(), password));
            response.setStatus(HttpServletResponse.SC_CREATED);
            return;
        }
        response.setContentType("text/plain");
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
