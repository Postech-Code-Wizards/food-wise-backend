package br.com.foodWise.foodWise.rest.converter;

import br.com.foodWise.foodWise.model.entities.Phone;
import br.com.foodWise.foodWise.rest.dtos.request.register.PhoneRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class PhoneRequestToEntityConverter
        implements Converter<PhoneRequest, Phone> {

    @Override
    public Phone convert(PhoneRequest source) {
        var phone = new Phone();
        phone.setAreaCode(source.getAreaCode());
        phone.setPhoneNumber(source.getPhoneNumber());
        phone.setPhoneType(source.getPhoneType());
        phone.setCreatedAt(ZonedDateTime.now());
        phone.setUpdatedAt(ZonedDateTime.now());
        return phone;
    }
}
