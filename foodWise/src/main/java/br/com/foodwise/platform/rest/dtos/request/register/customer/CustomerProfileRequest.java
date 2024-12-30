package br.com.foodwise.platform.rest.dtos.request.register.customer;

import br.com.foodwise.platform.rest.dtos.request.register.AddressRequest;
import br.com.foodwise.platform.rest.dtos.request.register.PhoneRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @Valid
    private AddressRequest address;

    @Valid
    private PhoneRequest phone;

}
