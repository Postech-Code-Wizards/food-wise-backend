package br.com.foodwise.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class MenuItem {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private boolean isAvailable;
    private String imageUrl;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Menu menu;
}
