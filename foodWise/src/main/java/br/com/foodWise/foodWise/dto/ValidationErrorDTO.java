package br.com.foodWise.foodWise.dto;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {
}
