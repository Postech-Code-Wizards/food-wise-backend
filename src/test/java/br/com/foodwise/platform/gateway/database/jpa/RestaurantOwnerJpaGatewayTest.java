package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantOwnerDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantOwnerEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantOwnerEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.RestaurantOwnerRepository;
import br.com.foodwise.platform.gateway.database.jpa.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantOwnerJpaGatewayTest {

    @Mock
    private RestaurantOwnerRepository restaurantOwnerRepository;

    @Mock
    private RestaurantOwnerDomainToEntityConverter restaurantOwnerDomainToEntityConverter;

    @Mock
    private RestaurantOwnerEntityToDomainConverter restaurantOwnerEntityToDomainConverter;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RestaurantOwnerJpaGateway restaurantOwnerJpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find RestaurantOwner by ID and convert to domain")
    void findById_ShouldFindAndConvert() {
        
        Long restaurantOwnerId = Instancio.create(Long.class);
        RestaurantOwnerEntity restaurantOwnerEntity = Instancio.create(RestaurantOwnerEntity.class);
        RestaurantOwner expectedRestaurantOwner = Instancio.create(RestaurantOwner.class);

        when(restaurantOwnerRepository.findById(restaurantOwnerId)).thenReturn(Optional.of(restaurantOwnerEntity));
        when(restaurantOwnerEntityToDomainConverter.convert(restaurantOwnerEntity)).thenReturn(expectedRestaurantOwner);

        RestaurantOwner actualRestaurantOwner = restaurantOwnerJpaGateway.findById(restaurantOwnerId);

        assertEquals(expectedRestaurantOwner, actualRestaurantOwner);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when RestaurantOwner is not found")
    void findById_ShouldThrowResourceNotFound() {
        
        Long restaurantOwnerId = Instancio.create(Long.class);

        when(restaurantOwnerRepository.findById(restaurantOwnerId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restaurantOwnerJpaGateway.findById(restaurantOwnerId));
    }

    @Test
    @DisplayName("Should save RestaurantOwner and convert from domain")
    void save_ShouldSaveAndConvert() {
        
        RestaurantOwner restaurantOwner = Instancio.create(RestaurantOwner.class);
        RestaurantOwnerEntity restaurantOwnerEntity = Instancio.create(RestaurantOwnerEntity.class);
        UserEntity userEntity = Instancio.create(UserEntity.class);
        User user = restaurantOwner.getUser();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(userEntity));
        when(restaurantOwnerDomainToEntityConverter.convert(restaurantOwner)).thenReturn(restaurantOwnerEntity);
        when(restaurantOwnerRepository.save(any(RestaurantOwnerEntity.class))).thenReturn(restaurantOwnerEntity);

        restaurantOwnerJpaGateway.save(restaurantOwner);

        verify(restaurantOwnerRepository, times(1)).save(restaurantOwnerEntity);
        assertEquals(userEntity, restaurantOwnerEntity.getUserEntity());
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when User is not found during save")
    void save_ShouldThrowResourceNotFound_WhenUserNotFound() {
        
        RestaurantOwner restaurantOwner = Instancio.create(RestaurantOwner.class);
        User user = restaurantOwner.getUser();

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restaurantOwnerJpaGateway.save(restaurantOwner));
    }

}