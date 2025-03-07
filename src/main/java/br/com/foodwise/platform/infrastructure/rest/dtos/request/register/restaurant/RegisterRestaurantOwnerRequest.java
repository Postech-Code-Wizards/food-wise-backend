package br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant;

import br.com.foodwise.platform.application.annotation.CpfOrCnpj;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRestaurantOwnerRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @CpfOrCnpj(message = "CPF ou CNPJ inválido")
    @NotBlank(message = "Business registration number is required")
    private String businessRegistrationNumber;

    @NotBlank(message = "Business e-mail is required")
    private String businessEmail;

}
