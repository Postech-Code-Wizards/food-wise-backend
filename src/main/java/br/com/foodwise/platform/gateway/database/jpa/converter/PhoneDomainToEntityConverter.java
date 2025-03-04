package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Phone;
import br.com.foodwise.platform.gateway.database.jpa.entities.PhoneEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneDomainToEntityConverter {

    public PhoneEntity convert(Phone source) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(source, PhoneEntity.class);
    }
}