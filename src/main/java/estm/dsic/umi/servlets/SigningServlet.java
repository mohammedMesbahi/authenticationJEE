package estm.dsic.umi.servlets;

import estm.dsic.umi.beans.User;
import estm.dsic.umi.services.DefaultAuthService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

@WebServlet(name = "signingServlet", urlPatterns = "/signing")
public class SigningServlet extends HttpServlet {
    @Resource(lookup = "jdbc/tp2_jee")
    DataSource myDB;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            myDB.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Retrieve user input
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");

        // Perform basic validation (you should add more thorough validation)
        ArrayList<String> errorMessages = new ArrayList<>();


        if (userEmail == null || userEmail.trim().isEmpty()) {
            errorMessages.add("Email is required.");
        }

        if (userPassword == null || userPassword.trim().isEmpty()) {
            errorMessages.add("Password is required.");
        }

        // Check credentials (replace this with your authentication logic)
        if (errorMessages.isEmpty()) {
            User user = DefaultAuthService.getInstance().authenticate(userEmail, userPassword);
            if (user == null) {
                errorMessages.add("Invalid email or password.");

            } else {
                // If credentials are valid, set the emailOfTheUser cookie
                Cookie emailCookie = new Cookie("email", user.getEmail());
                response.addCookie(emailCookie);
                // Redirect to home page
                response.sendRedirect("home.jsp");
                return;
            }
        }
        // If credentials are not valid, set error messages and redirect to signing page
        request.setAttribute("errorMessages", errorMessages);
        request.getRequestDispatcher("signing.jsp").forward(request, response);
    }
}

