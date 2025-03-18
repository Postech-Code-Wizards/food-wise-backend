package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.UserGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class FindUserByIdUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private FindUserByIdUseCase findUserByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find User by ID from gateway")
    void findUserById_shouldFindUser() {

        Long userId = Instancio.create(Long.class);
        User expectedUser = Instancio.create(User.class);

        when(userGateway.findUserById(userId)).thenReturn(expectedUser);

        User actualUser = findUserByIdUseCase.findUserById(userId);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("Should return null when User is not found by ID")
    void findUserById_shouldReturnNull_whenNotFound() {
        Long userId = Instancio.create(Long.class);

        when(userGateway.findUserById(userId)).thenReturn(null);

        User actualUser = findUserByIdUseCase.findUserById(userId);

        assertNull(actualUser);
    }
}