package SimsRESTServer.restservices;

import SimsRESTServer.handlers.IUserHandler;
import SimsRESTServer.response.Reply;
import models.PasswordHasher;
import models.User;
import utils.GsonUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserService {
    private static IUserHandler handler;
    private PasswordHasher passwordHasher = new PasswordHasher();

    public static void setHandler(IUserHandler handler) {
        UserService.handler = handler;
    }

    @POST
    @Path("/register")
    @Consumes("application/json")
    public Response register(String data) {
        User registerUser = GsonUtils.fromJson(data, User.class);

        Reply reply = handler.register(registerUser.getEmailaddress(), registerUser.getPassword());
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    public Response login(String data) {
        User user = GsonUtils.fromJson(data, User.class);

        Reply reply = handler.login(user.getEmailaddress(), user.getPassword());
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    /**
    @PUT
    @Path("/user/edit")
    public Response editUser(User user) {

    } */

    @GET
    @Path("/all")
    public Response getAllRegisteredUsers() {
        Reply reply = handler.getAll();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
