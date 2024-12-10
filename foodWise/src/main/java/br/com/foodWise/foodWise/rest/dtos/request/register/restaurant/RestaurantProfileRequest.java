package br.com.foodWise.foodWise.rest.dtos.request.register.restaurant;

import br.com.foodWise.foodWise.rest.dtos.request.register.AddressRequest;
import br.com.foodWise.foodWise.rest.dtos.request.register.PhoneRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
//import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class RestaurantProfileRequest {

    @NotNull
    @NotBlank
    private String businessName;

    private String description;

    @NotNull
    @NotBlank
    /*@Schema(
            description = "Specifies the operational business hours for the service.",
            example = "09:00-18:00",
            type = "string",
            pattern = "HH:mm-HH:mm"
    )*/
    private String businessHours;

    @NotNull
    @NotBlank
    /*@Schema(
            description = "Indicates the maximum delivery radius for the service, measured in kilometers.",
            example = "10",
            type = "short"
    )*/
    private Short deliveryRadius;

    @NotNull
    @NotBlank
    private String cuisineType;

    @NotNull
    @Valid
    private AddressRequest address;

    @Valid
    private PhoneRequest phone;


}
