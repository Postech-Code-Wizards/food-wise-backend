package br.com.foodwise.platform.rest.dtos.response;

import br.com.foodwise.platform.model.entities.Address;
import br.com.foodwise.platform.model.entities.Phone;
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
