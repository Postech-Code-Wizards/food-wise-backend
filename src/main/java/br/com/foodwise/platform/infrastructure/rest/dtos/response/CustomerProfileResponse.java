package br.com.foodwise.platform.infrastructure.rest.dtos.response;

import br.com.foodwise.platform.gateway.entities.AddressEntity;
import br.com.foodwise.platform.gateway.entities.PhoneEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileResponse {

    private String firstName;
    private String lastName;
    private AddressEntity addressEntity;
    private PhoneEntity phoneEntity;

}
