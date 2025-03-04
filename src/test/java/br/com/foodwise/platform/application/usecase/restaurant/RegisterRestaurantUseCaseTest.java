package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.database.jpa.repository.RestaurantProfileRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.foodwise.platform.factory.RequestFactory.buildValidRegisterRestaurantRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegisterRestaurantUseCaseTest {

//    @Mock
//    private ConvertToRestaurantProfileEntityUseCase convertToRestaurantProfileEntityUseCase;
//
//    @Mock
//    private RestaurantProfileRepository restaurantProfileRepository;
//
//    @Mock
//    private CreateUserUseCase createUserUseCase;
//
//    @InjectMocks
//    private RegisterRestaurantUseCase registerRestaurantUseCase;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void shouldRegisterRestaurantSuccessfully() {
//        var registerRestaurantRequest = buildValidRegisterRestaurantRequest();
//        var userRequest = registerRestaurantRequest.getUser();
//        var user = Instancio.create(UserEntity.class);
//
//        when(createUserUseCase.execute(userRequest.getEmail(),
//                userRequest.getPassword(),
//                UserType.RESTAURANT_OWNER))
//                .thenReturn(user);
//
//        var restaurantEntity = new RestaurantProfileEntity();
//        when(convertToRestaurantProfileEntityUseCase.execute(any()))
//                .thenReturn(restaurantEntity);
//
//        registerRestaurantUseCase.execute(registerRestaurantRequest);
//
//        verify(restaurantProfileRepository).save(restaurantEntity);
//    }

}