package SimsRESTServer.restservices;

import SimsRESTServer.handlers.IUserHandler;
import SimsRESTServer.response.Reply;
import models.PasswordHasher;
import models.User;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/SIMS")
public class UserService {
    private static IUserHandler handler;
    private PasswordHasher passwordHasher = new PasswordHasher();

    public static void setHandler(IUserHandler handler) {
        UserService.handler = handler;
    }

    //TODO: Implement class
    @POST
    @Path("/register")
    public Response register(User registerUser) {
        Reply reply = handler.register(registerUser.getEmailaddress(), registerUser.getPassword());
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/login")
    public Response login(User user) {
        Reply reply = handler.login(user.getEmailaddress(), user.getPassword());
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    /**
    @PUT
    @Path("/user/edit")
    public Response editUser(User user) {

    } */

    @GET
    @Path("/user/all")
    public Response getAllRegisteredUsers() {
        Reply reply = handler.getAll();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
