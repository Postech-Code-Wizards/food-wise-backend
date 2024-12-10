package br.com.foodWise.foodWise.rest.dtos.response;

import br.com.foodWise.foodWise.model.entities.Address;
import br.com.foodWise.foodWise.model.entities.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantProfileResponse {

    private String businessName;
    private String description;
    private String businessHours;
    private Short deliveryRadius;
    private String cuisineType;
    private boolean isOpen;
    private Address address;
    private Phone phone;

}
