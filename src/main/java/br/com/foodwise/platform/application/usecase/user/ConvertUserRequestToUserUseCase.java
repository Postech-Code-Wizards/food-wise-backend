package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.infrastructure.rest.converter.common.UserRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertUserRequestToUserUseCase {

    private final UserRequestToEntityConverter userRequestToEntityConverter;

    public User execute(UserRequest userRequest) {
        return userRequestToEntityConverter.convert(userRequest);
    }

}
