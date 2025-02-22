package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.domain.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UpdateUserEmailUseCase {

    private final UserRepository userRepository;
    private final ConvertUserRequestToUserUseCase convertUserRequestToUserUseCase;

    @Transactional
    public void execute(UserRequest userRequest, Long id, UserType userType) {
        var existingUser = userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType)
                .orElseThrow(() -> new ResourceNotFoundException("USER_DOES_NOT_EXIST", ""));

        var user = convertUserRequestToUserUseCase.execute(userRequest);

        if (ObjectUtils.isNotEmpty(user.getEmail()) && !userRequest.getPassword().equals(existingUser.getPassword())) {
            existingUser.setEmail(user.getEmail());
        }

        existingUser.setUpdatedAt(ZonedDateTime.now());
        existingUser.setActive(true);

        userRepository.save(existingUser);
    }

}
