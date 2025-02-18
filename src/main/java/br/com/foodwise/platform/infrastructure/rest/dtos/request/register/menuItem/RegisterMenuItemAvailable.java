package br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMenuItemAvailable {

    private Boolean available;
}
