package SimsRESTServer.handlers;

import SimsDal.repository.UserRepository;
import SimsRESTServer.response.ErrorJson;
import SimsRESTServer.response.LoggedInUserJson;
import SimsRESTServer.response.Reply;
import SimsRESTServer.response.Status;
import com.google.gson.Gson;
import logging.Logger;
import models.LoggedInUser;
import models.PasswordHasher;
import models.Token;
import models.User;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

public class UserHandler implements IUserHandler {
    private UserRepository repository;
    private Gson gson;
    private PasswordHasher passwordHasher;

    public UserHandler(UserRepository repository) {
        this.repository = repository;
        this.gson = new Gson();
        this.passwordHasher = new PasswordHasher();
    }

    @Override
    public Reply register(String emailaddress, String password) {
        User newUser = new User(emailaddress, passwordHasher.getPasswordHash(password));

        User saved = repository.save(newUser);

        if (saved.getUserId() != 0) {
            return new Reply(Status.OK, gson.toJson(saved));
        }
        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply login(String emailaddress, String password) {
        try {
            addUsers();
        } catch (Exception e) {
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }

        User user = new User(emailaddress, passwordHasher.getPasswordHash(password));
        List<User> userResponse = repository.findAll();

        if (userResponse.size() != 0) {
            for (User u : userResponse) {
                if (u.matches(user.getEmailaddress()) && passwordHasher.comparePasswordHash(password, u.getPassword())) {

                    LoggedInUserJson loggedInUserResponse = new LoggedInUserJson(u.getUserId(), u.getEmailaddress(), new Token(u.getTokenString(), u.getTokenCreationDate()));

                    String json = gson.toJson(loggedInUserResponse);
                    return new Reply(Status.OK, json);
                }
            }
        }
        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply editUser() {
        return null;
    }

    @Override
    public Reply getAll() {
        try {
            List<User> loggedInUsers = repository.findAll();
            List<LoggedInUserJson> loggedInUserResponse = new ArrayList<>();

            for (User u : loggedInUsers) {
                loggedInUserResponse.add(new LoggedInUserJson(u.getUserId(), u.getEmailaddress(), new Token(u.getTokenString(), u.getTokenCreationDate())));
            }

            String json = gson.toJson(loggedInUserResponse);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    private void addUsers() {
        Token token1 = Token.generate();
        User user1 = new User("henk@test.com", passwordHasher.getPasswordHash("Welkom01"), token1.getTokenText(), token1.getCreationDate());

        Token token2 = Token.generate();
        User user2 = new User("floortje@test.com", passwordHasher.getPasswordHash("lol"), token2.getTokenText(), token2.getCreationDate());

        repository.save(user1);
        repository.save(user2);
    }
}
