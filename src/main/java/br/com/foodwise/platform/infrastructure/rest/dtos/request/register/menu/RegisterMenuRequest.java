package br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMenuRequest {

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    private String description;

}
