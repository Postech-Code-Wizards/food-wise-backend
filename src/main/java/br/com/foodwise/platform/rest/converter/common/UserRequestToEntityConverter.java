package br.com.foodwise.platform.rest.converter.common;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRequestToEntityConverter implements Converter<UserRequest, User> {
    @Override
    public User convert(@NonNull UserRequest source) {
        var user = new User();

        var mapper = new ModelMapper();
        mapper.map(source, user);

        return user;
    }
}