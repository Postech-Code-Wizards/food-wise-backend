package br.com.foodwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String login;
    private String address;
    private LocalDateTime lastModifiedDateTime;

}
