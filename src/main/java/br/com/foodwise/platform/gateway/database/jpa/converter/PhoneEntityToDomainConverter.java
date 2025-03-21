package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Phone;
import br.com.foodwise.platform.gateway.database.jpa.entities.PhoneEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneEntityToDomainConverter {

    public Phone convert(PhoneEntity source) {
        return new Phone(
                source.getId(),
                source.getAreaCode(),
                source.getPhoneNumber(),
                source.getPhoneType(),
                source.getCreatedAt(),
                source.getUpdatedAt()
        );
    }
}
