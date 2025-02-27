package br.com.foodwise.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Phone {
    private Long id;
    private String areaCode;
    private String phoneNumber;
    private Phone phone;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
