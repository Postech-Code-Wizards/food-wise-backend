package br.com.foodWise.foodWise.rest.dtos.request.register;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRestaurantRequest {

    @NotNull(message = "User information is required")
    private UserRequest user;

    @NotNull(message = "Restaurant profile information is required")
    private RestaurantProfileRequest restaurant;

}
