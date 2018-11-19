package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "userId", nullable = false)
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int userId;

    @Column(name = "emailaddress", nullable = false)
    private String emailaddress;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "tokenstring")
    private String tokenString;

    @Column(name = "tokencreationdate")
    private Date tokenCreationDate;

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    public Date getTokenCreationDate() {
        return tokenCreationDate;
    }

    public void setTokenCreationDate(Date tokenCreationDate) {
        this.tokenCreationDate = tokenCreationDate;
    }

    // Constructors
    public User() {}

    public User(String emailaddress, String password) {
        this.emailaddress = emailaddress;
        this.password = password;
    }

    public User(int userId, String emailaddress, String password, String tokenString, Date tokenCreationDate) {
        this.userId = userId;
        this.emailaddress = emailaddress;
        this.password = password;
        this.tokenString = tokenString;
        this.tokenCreationDate = tokenCreationDate;
    }

    // Methods
    @Override
    public String toString() {
        return this.emailaddress;
    }

    public boolean matches(String emailaddress) {
        return this.getEmailaddress().equals(emailaddress);
    }
}
