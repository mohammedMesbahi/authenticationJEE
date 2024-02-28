package estm.dsic.umi.servlets;

import java.io.*;
import java.util.ArrayList;

import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.UserDaoJDBC;
import estm.dsic.umi.services.DefaultAuthService;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "DefaultServlet", value = {"/*"} )
public class DefaultServlet extends HttpServlet {
    private String message;
    @Resource(lookup = "jdbc/tp2_jee")
    DataSource myDB;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathInfo = request.getPathInfo();

        if (pathInfo.equals("/signup")) {
            String userName = request.getParameter("name");
            String userEmail = request.getParameter("email");
            String userPassword = request.getParameter("password");

            // Perform basic validation
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
            }
            else {
                User user = new User(userName, userEmail, userPassword);
                User dbUser = UserDaoJDBC.getInstance().create(user);
                if (dbUser != null) {
                    /*success message*/
                    successMessages.add("User created successfully.");
                    request.setAttribute("successMessages", successMessages);
                    request.getRequestDispatcher("signing.jsp").forward(request, response);
                } else {
                    errorMessages.add("User already exists.");
                    request.setAttribute("errorMessages", errorMessages);
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                }
            }
        }
        else if (pathInfo.equals("/signing")) {
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
                // TODO : debug
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
        else if (pathInfo.equals("/logout")) {
            // Remove the "emailOfTheUser" cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("email")) {
                        cookie.setMaxAge(0); // Set the cookie's maximum age to 0 for removal
                        response.addCookie(cookie);
                        break;
                    }
                }
            }

            // Redirect to the sign-in page
            response.sendRedirect("signing.jsp");
        } else {
               // not found
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/signup")) {
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } else if (pathInfo.equals("/signing")) {
            request.getRequestDispatcher("signing.jsp").forward(request, response);
        } else if (pathInfo.equals("/home")) {
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else if("/".equals(pathInfo)) {
            response.sendRedirect("signing.jsp");
        }
        else {
            // not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}