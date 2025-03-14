package br.com.foodwise.platform.domain.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CryptographyUtil {

    private CryptographyUtil() {
    }

    public static String getEncryptedPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
