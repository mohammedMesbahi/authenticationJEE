package estm.dsic.umi.controllers;

import estm.dsic.umi.beans.User;

public interface AuthController {
    User authenticate(User u);
    User register(User user);
    Void logout(String username);
}
