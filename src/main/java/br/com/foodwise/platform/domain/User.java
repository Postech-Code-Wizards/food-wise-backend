package br.com.foodwise.platform.domain;

import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.domain.utils.CryptographyUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private UserType userType;
    private boolean isActive;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime deletedAt;

    public void delete() {
        this.isActive = false;
        this.updatedAt = ZonedDateTime.now();
        this.deletedAt = ZonedDateTime.now();
    }

    public void registerUser(UserType userType) {
        this.userType = userType;
        this.password = CryptographyUtil.getEncryptedPassword(this.password);
        this.isActive = true;
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }
}