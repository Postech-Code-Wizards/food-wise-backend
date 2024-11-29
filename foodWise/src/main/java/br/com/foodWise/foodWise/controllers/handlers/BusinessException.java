package br.com.foodWise.foodWise.controllers.handlers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {

    private final String code;
    private final HttpStatus status;

}
