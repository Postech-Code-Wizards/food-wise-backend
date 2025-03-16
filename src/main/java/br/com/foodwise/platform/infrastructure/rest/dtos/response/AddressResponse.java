package br.com.foodwise.platform.infrastructure.rest.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String neighborhood;
    private String postalCode;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
