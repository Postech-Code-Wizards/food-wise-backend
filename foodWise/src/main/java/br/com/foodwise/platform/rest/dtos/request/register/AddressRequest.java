package br.com.foodwise.platform.rest.dtos.request.register;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2, message = "The State must be a 2 letter abbreviation.")
    private String state;

    @NotBlank(message = "Neighborhood is required")
    private String neighborhood;

    @NotBlank(message = "Postal code is required")
    @Pattern(regexp = "^[0-9]{5}(-[0-9]{3})?$", message = "Postal code must match the pattern XXXXX ou XXXXX-XXX")
    private String postalCode;

    @NotBlank(message = "Country is required")
    private String country;

    @DecimalMin(value = "-90.0", inclusive = true, message = "Latitude must be between -90.0 and 90.0")
    @DecimalMax(value = "90.0", inclusive = true, message = "Latitude must be between -90.0 and 90.0")
    private BigDecimal latitude;

    @DecimalMin(value = "-180.0", inclusive = true, message = "Latitude must be between -180.0 and 180.0")
    @DecimalMax(value = "180.0", inclusive = true, message = "Latitude must be between -180.0 and 180.0")
    private BigDecimal longitude;
}
