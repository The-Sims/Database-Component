package models;

import java.util.Calendar;
import java.util.Date;

public class Token {
    public static final int TTL_DEFAULT = 1800;

    private String TokenText;
    private Date CreationDate;
    private int ttl;

    // Getters and setters
    public String getTokenText() {
        return TokenText;
    }

    public void setTokenText(String tokenText) {
        TokenText = tokenText;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    // Constructors
    public Token() {

    }

    public Token(String tokenText, Date creationDate, int ttl) {
        this.TokenText = tokenText;
        this.CreationDate = creationDate;
        this.ttl = ttl;
    }

    public Token(String tokenText, Date creationDate) {
        this(tokenText, creationDate, TTL_DEFAULT);
    }

    // Methods
    public boolean isExpired() {
        Date today = new Date();
        Date tempDate;

        Calendar c = Calendar.getInstance();
        c.setTime(CreationDate);
        c.add(Calendar.DATE, ttl);

        tempDate = c.getTime();

        if (tempDate.equals(today)) {
            return true;
        }

        return false;
    }

    public static Token generate() {
        return new Token(java.util.UUID.randomUUID().toString(), new Date());
    }
}
