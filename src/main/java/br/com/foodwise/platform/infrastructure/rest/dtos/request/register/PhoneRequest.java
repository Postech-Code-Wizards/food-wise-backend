package br.com.foodwise.platform.infrastructure.rest.dtos.request.register;

import br.com.foodwise.platform.domain.enums.PhoneType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneRequest {

    @NotBlank(message = "Area code is required")
    private String areaCode;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^(\\+\\d{1,3})?\\d{10,11}$",
            message = "Phone number must have 10 or 11 digits, with an optional international code prefixed by '+'."
    )
    private String phoneNumber;

    @NotNull(message = "Phone type is required")
    private PhoneType phoneType;
}
