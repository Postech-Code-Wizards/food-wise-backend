package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.infrastructure.rest.converter.common.UserRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ConvertUserRequestToUserUseCaseTest {

    @Mock
    private UserRequestToEntityConverter userRequestToEntityConverter;

    @InjectMocks
    private ConvertUserRequestToUserUseCase convertUserRequestToUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldConvertUserRequestToUserWhenValidInputGiven() {

        UserRequest userRequest = Instancio.create(UserRequest.class);
        User user = Instancio.create(User.class);

        when(userRequestToEntityConverter.convert(userRequest)).thenReturn(user);

        convertUserRequestToUserUseCase.execute(userRequest);

        verify(userRequestToEntityConverter, times(1)).convert(userRequest);

    }

}