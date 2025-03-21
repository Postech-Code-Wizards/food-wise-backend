package br.com.foodwise.platform.factory;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.ZonedDateTime;

public class SecurityHelperFactory {

    public static UserEntity buildMockUser(String email, String password, UserType userType) {
        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setId(0L);
        mockUserEntity.setEmail(email);
        mockUserEntity.setPassword(password);
        mockUserEntity.setUserType(userType);
        mockUserEntity.setActive(true);
        mockUserEntity.setCreatedAt(ZonedDateTime.now());
        mockUserEntity.setUpdatedAt(ZonedDateTime.now());
        return mockUserEntity;
    }

    public static User buildMockUser() {
        return new User(
                0L,
                "email",
                "password",
                UserType.ADMIN,
                true,
                ZonedDateTime.now(),
                ZonedDateTime.now(),
                null
        );
    }

    public static void authenticateUser(String email, String password, UserType userType) {
        SecurityContextHolder.clearContext();

        var mockUser = buildMockUser(email, password, userType);
        var authentication = new UsernamePasswordAuthenticationToken(mockUser, null, mockUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
