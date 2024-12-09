package br.com.foodWise.foodWise.auth.dtos.request.register;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCustomerRequest {

    @NotNull(message = "User information is required")
    private UserRequest user;

    @NotNull(message = "Customer profile information is required")
    private CustomerProfileRequest customer;

}
