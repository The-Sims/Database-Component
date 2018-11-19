package models;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordHasherTest {
    private PasswordHasher passwordHasher;

    @Before
    public void TestInitialize() {
        passwordHasher = new PasswordHasher();
    }

    @Test
    public void TestPasswordHashing() {
        assertTrue(StringUtils.isNotBlank(passwordHasher.getPasswordHash("Password")));
    }

    @Test
    public void TestComparePasswordHash() {
        String testHash = passwordHasher.getPasswordHash("validHash");

        assertEquals(true, passwordHasher.comparePasswordHash("validHash", testHash));
        assertEquals(false, passwordHasher.comparePasswordHash("test", testHash));
    }
}