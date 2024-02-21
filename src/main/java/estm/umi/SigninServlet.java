package estm.umi;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signinServlet")
public class SigninServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        if ("user@example.com".equals(userEmail) && "password123".equals(userPassword)) {
            // If credentials are valid, set the emailOfTheUser cookie
            Cookie emailCookie = new Cookie("emailOfTheUser", userEmail);
            response.addCookie(emailCookie);
            // Redirect to home page
            response.sendRedirect("home.jsp");
        } else {
            // If credentials are not valid, set error messages and redirect to signin page
            request.setAttribute("errorMessages", errorMessages);
            request.getRequestDispatcher("signin.jsp").forward(request, response);
        }
    }
}

