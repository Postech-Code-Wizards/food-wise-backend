package br.com.foodwise.factory;

import br.com.foodwise.model.entities.User;
import br.com.foodwise.model.entities.enums.UserType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.ZonedDateTime;

public class SecurityHelperFactory {

    private static User buildMockUser(String email, String password, UserType userType) {
        User mockUser = new User();
        mockUser.setId(0L);
        mockUser.setEmail(email);
        mockUser.setPassword(password);
        mockUser.setUserType(userType);
        mockUser.setActive(true);
        mockUser.setCreatedAt(ZonedDateTime.now());
        mockUser.setUpdatedAt(ZonedDateTime.now());
        return mockUser;
    }


    public static void authenticateUser(String email, String password, UserType userType) {
        var mockUser = buildMockUser(email, password, userType);
        var authentication = new UsernamePasswordAuthenticationToken(mockUser, null, mockUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
