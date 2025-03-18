package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.gateway.database.jpa.entities.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDomainToEntityConverter {

    public AddressEntity convert(Address source) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(source, AddressEntity.class);
    }
}