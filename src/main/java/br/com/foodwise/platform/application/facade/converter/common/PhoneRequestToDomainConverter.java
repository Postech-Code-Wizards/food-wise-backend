package br.com.foodwise.platform.application.facade.converter.common;

import br.com.foodwise.platform.domain.Phone;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PhoneRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneRequestToDomainConverter {

    public Phone convert(PhoneRequest source) {
        return new Phone(
                null,
                source.getAreaCode(),
                source.getPhoneNumber(),
                source.getPhoneType(),
                null,
                null
        );
    }
}
