package br.com.foodwise.platform.infrastructure.rest.dtos.response;

import br.com.foodwise.platform.gateway.database.jpa.entities.AddressEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.PhoneEntity;
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
    private AddressEntity addressEntity;
    private PhoneEntity phoneEntity;

}
