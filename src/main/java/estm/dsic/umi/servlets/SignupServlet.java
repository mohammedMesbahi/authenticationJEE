package estm.dsic.umi.servlets;

import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.UserDaoJDBC;

import java.io.*;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "SignupServlet", urlPatterns = "/signup")
public class SignupServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("name");
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");

        // Perform basic validation (you should add more thorough validation)
        ArrayList<String> errorMessages = new ArrayList<>();
        ArrayList<String> successMessages = new ArrayList<>();

        if (userName == null || userName.trim().isEmpty()) {
            errorMessages.add("Name is required.");
        }

        if (userEmail == null || userEmail.trim().isEmpty()) {
            errorMessages.add("Email is required.");
        }

        if (userPassword == null || userPassword.trim().isEmpty()) {
            errorMessages.add("Password is required.");
        }
        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        User user = new User(userName, userEmail, userPassword);
        User dbUser = UserDaoJDBC.getInstance().create(user);
        if (dbUser != null) {
            /*success message*/
            successMessages.add("User created successfully.");
            request.setAttribute("successMessage", successMessages);
            request.getRequestDispatcher("signing.jsp").forward(request, response);
        } else {
            errorMessages.add("User already exists.");
            request.setAttribute("errorMessages", errorMessages);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}