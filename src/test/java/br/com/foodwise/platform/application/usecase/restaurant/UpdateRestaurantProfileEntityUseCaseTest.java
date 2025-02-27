package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.gateway.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.gateway.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Optional;

import static br.com.foodwise.platform.factory.RequestFactory.buildRestaurantProfileEntity;
import static br.com.foodwise.platform.factory.RequestFactory.buildRestaurantProfileRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UpdateRestaurantProfileEntityUseCaseTest {

    @Mock
    private RestaurantProfileRepository restaurantProfileRepository;

    @Mock
    private ConvertToRestaurantProfileEntityUseCase convertToRestaurantProfileEntityUseCase;

    @InjectMocks
    private UpdateRestaurantProfileUseCase updateRestaurantProfileUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success case for Restaurant Profile Update")
    void shouldUpdateRestaurantProfileSuccessfully() {
        RestaurantProfileRequest restaurantNewData = buildRestaurantProfileRequest();
        var restaurantProfile = buildRestaurantProfileEntity();

        when(restaurantProfileRepository.findById(anyLong())).thenReturn(Optional.of(restaurantProfile));

        var restaurantProfileEntity = new RestaurantProfileEntity();
        restaurantProfileEntity.setUpdatedAt(ZonedDateTime.now());
        when(convertToRestaurantProfileEntityUseCase.execute(any())).thenReturn(restaurantProfileEntity);

        updateRestaurantProfileUseCase.execute(restaurantNewData, anyLong());

        verify(restaurantProfileRepository, times(1)).findById(anyLong());
        assertEquals(restaurantProfileEntity.getBusinessName(), restaurantProfile.getBusinessName());
        assertEquals(restaurantProfileEntity.getAddressEntity(), restaurantProfile.getAddressEntity());
        assertEquals(restaurantProfileEntity.getPhoneEntity(), restaurantProfile.getPhoneEntity());
        assertNotNull(restaurantProfileEntity.getUpdatedAt());
    }

    @Test
    @DisplayName("Fail case for Restaurant Profile Update")
    void shouldThrowExceptionForNotFindingRestaurantProfile() {
        RestaurantProfileRequest restaurantNewData = buildRestaurantProfileRequest();

        long nonExistentUserId = 500000000L;

        doReturn(Optional.empty()).when(restaurantProfileRepository).findById(nonExistentUserId);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> updateRestaurantProfileUseCase.execute(restaurantNewData, nonExistentUserId)
        );

        assertEquals("RESTAURANT_DOES_NOT_EXIST", exception.getCode());

        verify(restaurantProfileRepository, times(1)).findById(nonExistentUserId);

        verify(restaurantProfileRepository, never()).save(any());
    }

}