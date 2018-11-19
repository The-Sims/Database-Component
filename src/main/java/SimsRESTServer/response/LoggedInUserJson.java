package SimsRESTServer.response;

import models.Token;

public class LoggedInUserJson {
    private int userId;
    private String emailaddress;
    private Token token;

    public int getUserId() {
        return userId;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public Token getToken() {
        return token;
    }

    public LoggedInUserJson(int userId, String emailaddress, Token token) {
        this.userId = userId;
        this.emailaddress = emailaddress;
        this.token = token;
    }
}
