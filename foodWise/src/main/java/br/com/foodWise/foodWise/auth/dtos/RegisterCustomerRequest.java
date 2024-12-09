package br.com.foodWise.foodWise.auth.dtos;

import br.com.foodWise.foodWise.model.entities.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCustomerRequest {
    private String email;
    private String password;
    private UserType role;

}