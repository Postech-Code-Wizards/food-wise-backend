package br.com.foodwise.platform.domain;

import br.com.foodwise.platform.domain.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Phone {
    private Long id;
    private String areaCode;
    private String phoneNumber;
    private PhoneType phoneType;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
