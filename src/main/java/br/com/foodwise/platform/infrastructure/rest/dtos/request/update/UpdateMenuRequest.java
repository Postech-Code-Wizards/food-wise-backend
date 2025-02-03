package br.com.foodwise.platform.infrastructure.rest.dtos.request.update;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMenuRequest {
    @Size(min = 1, max = 100)
    private String name;

    private String description;
}
