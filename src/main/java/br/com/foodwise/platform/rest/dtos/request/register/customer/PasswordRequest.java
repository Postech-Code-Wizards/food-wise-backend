package br.com.foodwise.platform.rest.dtos.request.register.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRequest {


    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    private String newPassword;

}
