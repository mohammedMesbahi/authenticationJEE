package estm.dsic.umi.controllers;

import estm.dsic.umi.beans.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
public class DefaultAuthController implements AuthController{
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public User authenticate(User u) {
        System.out.println("Authenticating user with email: " + u.getEmail());
        return u;
    }

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public Void logout(String username) {
        return null;
    }
    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        return "Hello";
    }
}
