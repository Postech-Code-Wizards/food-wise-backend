package br.com.foodwise.platform.rest.dtos.request.register;

import br.com.foodwise.platform.model.entities.enums.PhoneType;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "\\d+", message = "Phone number must only contain digits")
    @Size(min = 11, max = 11, message = "Phone number must have 11 digits xx xxxxx xxxx")
    private String phoneNumber;

    @NotNull(message = "Phone type is required")
    private PhoneType phoneType;
}
