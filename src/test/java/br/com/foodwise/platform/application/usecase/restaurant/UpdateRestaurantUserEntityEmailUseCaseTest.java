package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.application.usecase.user.UpdateUserEmailUseCase;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.database.jpa.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateRestaurantUserEntityEmailUseCaseTest {

//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private ConvertUserRequestToUserUseCase convertUserRequestToUserUseCase;
//
//    @Mock
//    private UpdateUserEmailUseCase updateUserEmailUseCase;
//
//    @InjectMocks
//    private UpdateRestaurantUserEmailUseCase updateRestaurantUserEmailUseCase;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void updateUserEmailIsSuccess(){
//
//        UserRequest userRequest = Instancio.create(UserRequest.class);
//        Long userId = Instancio.create(Long.class);
//        UserEntity userEntity = Instancio.create(UserEntity.class);
//
//        doNothing().when(updateUserEmailUseCase).execute(userRequest, userId, UserType.RESTAURANT_OWNER);
//        when(userRepository.findByIdAndUserTypeAndDeletedAtIsNull(userId, UserType.RESTAURANT_OWNER)).thenReturn(Optional.ofNullable(userEntity));
//        when(convertUserRequestToUserUseCase.execute(userRequest)).thenReturn(userEntity);
//
//        updateRestaurantUserEmailUseCase.execute(userRequest, userId);
//
//        verify(updateUserEmailUseCase, times(1)).execute(userRequest, userId, UserType.RESTAURANT_OWNER);
//    }
//
//    @Test
//    void testExecuteThrowsException() {
//
//        UserRequest userRequest = Instancio.create(UserRequest.class);
//        Long userId = Instancio.create(Long.class);
//
//        when(userRepository.findByIdAndUserTypeAndDeletedAtIsNull(userId, UserType.RESTAURANT_OWNER)).thenReturn(Optional.empty());
//
//        doThrow(new RuntimeException("Erro ao atualizar e-mail")).when(updateUserEmailUseCase)
//                .execute(userRequest, userId, UserType.RESTAURANT_OWNER);
//
//        assertThrows(RuntimeException.class, () -> updateRestaurantUserEmailUseCase.execute(userRequest, userId));
//
//    }

}