package br.com.foodwise.platform.gateway.database.jpa.entities.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class CryptographyUtilTest {

    @Test
    @DisplayName("Should encrypt a password and verify it matches")
    void should_encrypt_password_and_verify_matches() {

        String password = "mySecretPassword";

        String encryptedPassword = CryptographyUtil.getEncryptedPassword(password);

        assertNotNull(encryptedPassword);
        assertNotEquals(password, encryptedPassword);
        assertTrue(new BCryptPasswordEncoder().matches(password, encryptedPassword));
    }

    @Test
    @DisplayName("Should encrypt the same password to a different value each time")
    void should_encrypt_same_password_to_different_value_each_time() {

        String password = "mySecretPassword";

        String encryptedPassword1 = CryptographyUtil.getEncryptedPassword(password);
        String encryptedPassword2 = CryptographyUtil.getEncryptedPassword(password);

        assertNotEquals(encryptedPassword1, encryptedPassword2);
        assertTrue(new BCryptPasswordEncoder().matches(password, encryptedPassword1));
        assertTrue(new BCryptPasswordEncoder().matches(password, encryptedPassword2));
    }

    @Test
    @DisplayName("Should encrypt an empty password")
    void should_encrypt_empty_password(){

        String password = "";

        String encryptedPassword = CryptographyUtil.getEncryptedPassword(password);

        assertNotNull(encryptedPassword);
        assertTrue(new BCryptPasswordEncoder().matches(password, encryptedPassword));
    }

}