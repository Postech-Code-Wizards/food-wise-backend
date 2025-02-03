package br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant;

import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.AddressRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PhoneRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RestaurantProfileRequest {

    @NotBlank(message = "Business name is required")
    private String businessName;

    private String description;

    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):([0-5][0-9])-([01]?[0-9]|2[0-3]):([0-5][0-9])$",
            message = "Business hours must match the pattern HH:mm-HH:mm")
    @Schema(
            description = "Specifies the operational business hours for the service.",
            example = "09:00-18:00",
            type = "string",
            pattern = "HH:mm-HH:mm"
    )
    private String businessHours;

    @NotNull(message = "Delivery radius is required")
    @Min(1)
    @Schema(
            description = "Indicates the maximum delivery radius for the service, measured in kilometers.",
            example = "10",
            type = "short"
    )
    private Short deliveryRadius;

    @NotBlank(message = "Cuisine type is required")
    private String cuisineType;

    @NotNull(message = "Address is required")
    @Valid
    private AddressRequest address;

    @Valid
    private PhoneRequest phone;

}
