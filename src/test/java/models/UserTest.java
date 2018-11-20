package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private PasswordHasher passwordHasher;

    @Before
    public void  TestInitialize() {
        passwordHasher = new PasswordHasher();
    }

    @Test
    public void TestFirstConstructor() {
        User test = new User("test@test.com", passwordHasher.getPasswordHash("test"));

        assertEquals(test.getEmailaddress(), "test@test.com");
        assertTrue(passwordHasher.comparePasswordHash("test", test.getPassword()));
    }

    @Test
    public void TestSecondConstructor() {
        Token token = Token.generate();
        User test = new User(1, "test@test.com", passwordHasher.getPasswordHash("test"), token.getTokenText(), token.getCreationDate());

        assertEquals(test.getEmailaddress(), "test@test.com");
        assertTrue(passwordHasher.comparePasswordHash("test", test.getPassword()));
        assertEquals(test.getTokenString(), token.getTokenText());
        assertEquals(test.getTokenCreationDate(), token.getCreationDate());
    }

    @Test
    public void TestUserEmailMatches() {
        User test = new User("test@test.com", passwordHasher.getPasswordHash("test"));

        assertTrue(test.matches("test@test.com"));
    }
}