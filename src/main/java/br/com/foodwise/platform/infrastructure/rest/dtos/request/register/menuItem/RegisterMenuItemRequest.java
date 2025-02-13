package br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem;

import br.com.foodwise.platform.domain.entities.Menu;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMenuItemRequest {

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    @Size(min = 1, max = 50)
    private String category;

    @NotNull
    @Size(min = 1, max = 254)
    private String imageUrl;

    @NotNull
    private Menu menu;
}
