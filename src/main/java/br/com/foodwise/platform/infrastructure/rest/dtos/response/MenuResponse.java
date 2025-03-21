package br.com.foodwise.platform.infrastructure.rest.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {
    private Long id;
    private String name;
    private String description;
    private RestaurantProfileResponse restaurant;
}
