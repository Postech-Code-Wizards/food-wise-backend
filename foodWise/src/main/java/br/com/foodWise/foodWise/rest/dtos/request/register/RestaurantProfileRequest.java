package br.com.foodWise.foodWise.rest.dtos.request.register;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RestaurantProfileRequest {

    @NotNull
    @NotBlank
    private String businessName;

    private String description;

    @NotNull
    @NotBlank
    private String businessHours;

    @NotNull
    @NotBlank
    private Short deliveryRadius;

    @NotNull
    @NotBlank
    private String cuisineType;

    @NotNull
    @NotBlank
    private boolean isOpen;

    @NotNull
    @Valid
    private AddressRequest address;

    @Valid
    private PhoneRequest phone;


}
