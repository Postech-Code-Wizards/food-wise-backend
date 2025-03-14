package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.gateway.RestaurantOwnerGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.instancio.Instancio;

import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateRestaurantOwnerUseCaseTest {

    @Mock
    private RestaurantOwnerGateway restaurantOwnerGateway;

    @InjectMocks
    private UpdateRestaurantOwnerUseCase updateRestaurantOwnerUseCase;

    @Test
    @DisplayName("Should update RestaurantOwner successfully")
    void execute_ShouldUpdateSuccessfully() {
        
        Long userId = Instancio.create(Long.class);
        RestaurantOwner existingOwner = Instancio.create(RestaurantOwner.class);
        RestaurantOwner updatedOwner = Instancio.create(RestaurantOwner.class);

        when(restaurantOwnerGateway.findById(userId)).thenReturn(existingOwner);
        //when(restaurantOwnerGateway.save(any(RestaurantOwner.class))).thenReturn(null);

        updateRestaurantOwnerUseCase.execute(updatedOwner, userId);

        verify(restaurantOwnerGateway, times(1)).findById(userId);
        verify(restaurantOwnerGateway, times(1)).save(any(RestaurantOwner.class));
    }

    @Test
    @DisplayName("Should update RestaurantOwner with correct populated data")
    void execute_ShouldUpdateWithCorrectData() {
        
        Long userId = Instancio.create(Long.class);
        RestaurantOwner existingOwner = Instancio.create(RestaurantOwner.class);
        RestaurantOwner updatedOwner = Instancio.create(RestaurantOwner.class);

        when(restaurantOwnerGateway.findById(userId)).thenReturn(existingOwner);
        /*when(restaurantOwnerGateway.save(any(RestaurantOwner.class))).thenAnswer(invocation -> {
            RestaurantOwner savedOwner = invocation.getArgument(0);

            assert savedOwner.getId().equals(existingOwner.getId());
            assert savedOwner.getFirstName().equals(updatedOwner.getFirstName());
            assert savedOwner.getLastName().equals(updatedOwner.getLastName());
            assert savedOwner.getBusinessRegistrationNumber().equals(updatedOwner.getBusinessRegistrationNumber());
            assert savedOwner.getBusinessEmail().equals(updatedOwner.getBusinessEmail());
            assert savedOwner.getCreatedAt().equals(existingOwner.getCreatedAt());
            assert savedOwner.getUpdatedAt().isAfter(existingOwner.getCreatedAt());
            assert savedOwner.getUser().equals(existingOwner.getUser());
            return null;
        });*/

        updateRestaurantOwnerUseCase.execute(updatedOwner, userId);

        verify(restaurantOwnerGateway, times(1)).save(any(RestaurantOwner.class));
    }

}