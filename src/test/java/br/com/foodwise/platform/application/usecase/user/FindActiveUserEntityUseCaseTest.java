package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.UserGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindActiveUserEntityUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private FindActiveUserUseCase findActiveUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given an active user email, should return the correct user")
    void shouldFindActiveUserSuccessfully() {

        long id = Instancio.create(Long.class);
        User user = Instancio.create(User.class);

        when(userGateway.findByIdAndUserTypeAndDeletedAtIsNull(id, UserType.CUSTOMER)).thenReturn(Optional.of(user));

        findActiveUserUseCase.execute(id, UserType.CUSTOMER);

        verify(userGateway, times(1)).findByIdAndUserTypeAndDeletedAtIsNull(id, UserType.CUSTOMER);
    }

}