package br.com.foodwise.rest.dtos.request.register.restaurant;

import br.com.foodwise.rest.dtos.request.register.UserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRestaurantRequest {

    @Valid
    @NotNull(message = "User information is required")
    private UserRequest user;

    @Valid
    @NotNull(message = "Restaurant profile information is required")
    private RestaurantProfileRequest restaurant;

}
