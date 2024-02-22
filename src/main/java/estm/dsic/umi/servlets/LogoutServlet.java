package estm.dsic.umi.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Remove the "emailOfTheUser" cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("emailOfTheUser")) {
                    cookie.setMaxAge(0); // Set the cookie's maximum age to 0 for removal
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        // Redirect to the sign-in page
        response.sendRedirect("signin.jsp");
    }
}

