package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.gateway.entities.UserEntity;
import br.com.foodwise.platform.infrastructure.rest.converter.common.UserRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertUserRequestToUserUseCase {

    private final UserRequestToEntityConverter userRequestToEntityConverter;

    public UserEntity execute(UserRequest userRequest) {
        return userRequestToEntityConverter.convert(userRequest);
    }

}
