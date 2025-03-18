package br.com.foodwise.platform.infrastructure.rest.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsDeliveryRestaurantResponse {

    private Long id;
    private String businessName;
    private Boolean isDeliveryOrder;

}
