package br.com.foodWise.foodWise.rest.converter.common;

import br.com.foodWise.foodWise.model.entities.Phone;
import br.com.foodWise.foodWise.rest.dtos.request.register.PhoneRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneRequestToEntityConverter
        implements Converter<PhoneRequest, Phone> {

    @Override
    public Phone convert(PhoneRequest source) {
        var phone = new Phone();

        var mapper = new ModelMapper();
        mapper.map(source, phone);

        return phone;
    }
}
