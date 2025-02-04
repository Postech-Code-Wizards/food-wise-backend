package br.com.foodwise.platform.infrastructure.rest.dtos.response;

import br.com.foodwise.platform.domain.entities.Address;
import br.com.foodwise.platform.domain.entities.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileResponse {

    private String firstName;
    private String lastName;
    private Address address;
    private Phone phone;

}
