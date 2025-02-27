package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.gateway.entities.UserEntity;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.repository.UserRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class FindActiveUserEntityUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FindActiveUserUseCase findActiveUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success case for User Email Update")
    void shouldFindActiveUserSuccessfully() {

        long id = Instancio.create(Long.class);
        UserEntity userEntity = Instancio.create(UserEntity.class);

        when(userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, UserType.CUSTOMER)).thenReturn(Optional.of(userEntity));

        findActiveUserUseCase.execute(id, UserType.CUSTOMER);

        verify(userRepository, times(1)).findByIdAndUserTypeAndDeletedAtIsNull(id, UserType.CUSTOMER);
    }

}