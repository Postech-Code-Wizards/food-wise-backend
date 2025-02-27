package br.com.foodwise.platform.domain;

import br.com.foodwise.platform.domain.enums.UserType;
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

}
