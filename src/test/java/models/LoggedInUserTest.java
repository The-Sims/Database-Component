package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoggedInUserTest {

    @Test
    public void TestConstructor() {
        Token token = Token.generate();
        LoggedInUser test = new LoggedInUser(1, "test@test.com", token);

        assertEquals(test.getUserId(), 1);
        assertEquals(test.getEmailaddress(), "test@test.com");
        assertEquals(test.getToken().getCreationDate(), token.getCreationDate());
        assertEquals(test.getToken().getTokenText(), token.getTokenText());
    }

}