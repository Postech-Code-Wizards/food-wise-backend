package br.com.foodwise.platform.rest.dtos.request.register.customer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @Min(8)
    private String newPassword;

}
