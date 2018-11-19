package models;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    // Link to source: https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/

    private String hashPassword (String password) {
        String generatedHash = BCrypt.hashpw(password, BCrypt.gensalt(12));

        return generatedHash;
    }

    public String getPasswordHash(String password) {
        return hashPassword(password);
    }

    public boolean comparePasswordHash(String password, String hashedPassword) {
        if (password == null | hashedPassword == null) {
            return false;
        }
        else {
            if (BCrypt.checkpw(password, hashedPassword)) {
                return true;
            }
        }

        return false;
    }
}
