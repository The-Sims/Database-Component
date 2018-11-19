package SimsRESTServer.handlers;

import SimsRESTServer.response.Reply;

public interface IUserHandler {
    Reply register(String emailaddress, String password);
    Reply login(String emailaddress, String password);
    Reply editUser();
    Reply getAll();
}
