package br.com.foodwise.platform.rest.dtos.request.register.customer;

import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCustomerRequest {

    @Valid
    @NotNull(message = "User information is required")
    private UserRequest user;

    @Valid
    @NotNull(message = "Customer profile information is required")
    private CustomerProfileRequest customer;

}
