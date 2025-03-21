package br.com.foodwise.platform.infrastructure.rest.dtos.request.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "E-mail format not valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

}
