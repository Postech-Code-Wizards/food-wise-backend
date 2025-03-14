package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.domain.enums.UserType;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteRestaurantProfileEntityUseCaseTest {

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @InjectMocks
    private DeleteRestaurantProfileUseCase deleteRestaurantProfileUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Must delete restaurant profile successfully")
    void deleteRestaurantProfileIsSuccessful() {

        var id = Instancio.create(long.class);
        deleteRestaurantProfileUseCase.execute(id);
        verify(deleteUserUseCase, times(1)).execute(id, UserType.RESTAURANT_OWNER);

    }

}