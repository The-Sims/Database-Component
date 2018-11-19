package SimsRESTServer.handlers;

import SimsDal.repository.UserRepository;
import SimsRESTServer.response.Reply;
import com.google.gson.Gson;
import models.PasswordHasher;
import models.User;

public class UserHandler implements IUserHandler {
    private UserRepository repository;
    private Gson gson;
    private PasswordHasher passwordHasher;

    public UserHandler(UserRepository repository) {
        this.repository = repository;
        this.gson = new Gson();
        this.passwordHasher = new PasswordHasher();
    }
    //TODO: Add checks + error handling + implement
    @Override
    public Reply register(String emailaddress, String password) {
        return null;
    }

    @Override
    public Reply login(String emailaddress, String password) {
        return null;
    }

    @Override
    public Reply editUser() {
        return null;
    }

    @Override
    public Reply getAll() {
        return null;
    }
}
