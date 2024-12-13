package br.com.foodWise.rest.dtos.response;

import br.com.foodWise.foodWise.model.entities.Address;
import br.com.foodWise.foodWise.model.entities.Phone;
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
