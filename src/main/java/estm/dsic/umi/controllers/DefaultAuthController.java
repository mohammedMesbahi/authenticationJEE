package estm.dsic.umi.controllers;

import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.UserDaoJDBC;
import estm.dsic.umi.dao.interfaces.UserDao;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class DefaultAuthController implements AuthController {
    private UserDao userDoa;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response authenticate(User u, @Context HttpServletResponse response) {
        userDoa = new UserDaoJDBC();
        User user = userDoa.get(u.getEmail(), u.getPassword());
        if (user != null) {
            // return user and create a cookie for the user
            // Create a cookie
            Cookie authCookie = new Cookie("token", user.getEmail() + ":" + user.getPassword());
            // Set the cookie properties (optional)
            authCookie.setMaxAge(3600);  // Set the cookie to expire in 1 hour (in seconds)
            authCookie.setPath("/");  // Cookie is accessible across the entire application
            // Add the cookie to the response
            response.addCookie(authCookie);
            return Response.status(Response.Status.OK).entity("Login successful").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid Credentials. Please try again.")
                    .build();
        }
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response register(User user) {
        // check if all fields are valid
        if (user.getEmail() == null ||
                user.getPassword() == null ||
                user.getName() == null ||
                user.getEmail().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getName().isEmpty()) {

            // message : "All fields are required"
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("All field are required")
                    .build();
        }
        userDoa = new UserDaoJDBC();
        User newUser = userDoa.create(user);
        if (newUser != null) {
            return Response.status(Response.Status.CREATED).entity(newUser).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error while creating user")
                    .build();
        }
    }

    @POST
    @Path("logout")
    public Response logout(@Context HttpServletResponse response) {
        // Invalidate the existing authentication cookie (assuming you have one)
        Cookie authCookie = new Cookie("token", "");
        authCookie.setMaxAge(0);  // Set the cookie to expire immediately
        authCookie.setPath("/");  // Cookie path should match the path used during login
        response.addCookie(authCookie);

        // Perform any other logout-related operations

        return Response.status(Response.Status.OK).entity("Logout successful").build();
    }

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello";
    }
}
