package models;

public class LoggedInUser {
    private int userId;
    private String emailaddress;
    private Token token;

    public int getUserId() {
        return userId;
    }

    public String getEmailaddress() {
        return  emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    // Constructors
    public LoggedInUser() {

    }

    public LoggedInUser(int userId, Token token) {
        this.userId = userId;
        this.token = token;
    }

    public LoggedInUser(int userId, String emailaddress, Token token) {
        this.userId = userId;
        this.emailaddress = emailaddress;
        this.token = token;
    }

    // Methods
    @Override
    public String toString() {
        return this.getEmailaddress();
    }

    public boolean matches(String emailaddress) {
        return this.getEmailaddress().equals(emailaddress);
    }
}
