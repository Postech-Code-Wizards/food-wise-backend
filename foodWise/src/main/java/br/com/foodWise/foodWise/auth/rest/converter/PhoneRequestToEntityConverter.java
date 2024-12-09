package br.com.foodWise.foodWise.auth.rest.converter;
import java.time.ZonedDateTime;

import br.com.foodWise.foodWise.auth.dtos.request.register.PhoneRequest;
import br.com.foodWise.foodWise.model.entities.Phone;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneRequestToEntityConverter
        implements Converter<PhoneRequest, Phone> {

    @Override
    public Phone convert(PhoneRequest source) {
        var phone = new Phone();
        phone.setPhoneType(source.getPhoneType());
        phone.setCreatedAt(ZonedDateTime.now());
        phone.setUpdatedAt(ZonedDateTime.now());
        return phone;
    }
}
