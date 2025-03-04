package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDomainToEntityConverter {

    public UserEntity convert(User source) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(source, UserEntity.class);
    }
}
