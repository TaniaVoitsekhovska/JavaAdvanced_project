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
            userService.create(new User(firstName, lastName, email, UserRole.USER.toString(), password));
            request.setAttribute("userEmail", email);
            request.getRequestDispatcher("cabinet.jsp").forward(request, response);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}