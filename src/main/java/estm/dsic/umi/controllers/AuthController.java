package estm.dsic.umi.controllers;

import estm.dsic.umi.beans.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

public interface AuthController {
    Response authenticate(User u, HttpServletResponse response);
    Response register(User user);
    Response logout(HttpServletResponse response);
}
