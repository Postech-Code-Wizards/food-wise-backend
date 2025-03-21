package br.com.foodwise.platform.infrastructure.rest.dtos.response;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.domain.Phone;
import br.com.foodwise.platform.domain.RestaurantOwner;
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
    private Boolean isDeliveryOrder;
    private Address address;
    private Phone phone;
    private RestaurantOwner restaurantOwner;

}
