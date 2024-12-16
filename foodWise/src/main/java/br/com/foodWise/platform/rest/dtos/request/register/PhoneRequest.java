package br.com.foodwise.platform.rest.dtos.request.register;

import br.com.foodwise.platform.model.entities.enums.PhoneType;
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
    @Pattern(regexp = "\\d+", message = "Phone number must only contain digits")
    private String phoneNumber;

    @NotNull(message = "Phone type is required")
    private PhoneType phoneType;
}
