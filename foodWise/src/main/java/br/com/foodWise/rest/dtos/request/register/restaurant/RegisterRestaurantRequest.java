package br.com.foodWise.rest.dtos.request.register.restaurant;

import br.com.foodWise.rest.dtos.request.register.UserRequest;
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
