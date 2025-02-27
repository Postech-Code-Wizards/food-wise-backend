package br.com.foodwise.platform.infrastructure.rest.converter.common;

import br.com.foodwise.platform.gateway.entities.UserEntity;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRequestToEntityConverter implements Converter<UserRequest, UserEntity> {
    @Override
    public UserEntity convert(@NonNull UserRequest source) {
        var user = new UserEntity();

        var mapper = new ModelMapper();
        mapper.map(source, user);

        return user;
    }
}