package br.com.foodwise.platform.infrastructure.rest.converter.common;

import br.com.foodwise.platform.gateway.entities.PhoneEntity;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PhoneRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneRequestToEntityConverter
        implements Converter<PhoneRequest, PhoneEntity> {

    @Override
    public PhoneEntity convert(PhoneRequest source) {
        var phone = new PhoneEntity();

        var mapper = new ModelMapper();
        mapper.map(source, phone);

        return phone;
    }
}
