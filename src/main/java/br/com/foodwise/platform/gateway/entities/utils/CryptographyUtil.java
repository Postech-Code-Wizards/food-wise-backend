package br.com.foodwise.platform.gateway.entities.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CryptographyUtil {

    public static String getEncryptedPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
