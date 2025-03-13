package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantProfileDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.RestaurantProfileEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.RestaurantProfileRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RestaurantProfileJpaGatewayTest {

    @Mock
    private RestaurantProfileRepository restaurantProfileRepository;

    @Mock
    private RestaurantProfileDomainToEntityConverter restaurantProfileDomainToEntityConverter;

    @Mock
    private RestaurantProfileEntityToDomainConverter restaurantProfileEntityToDomainConverter;

    @InjectMocks
    private RestaurantProfileJpaGateway restaurantProfileJpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find RestaurantProfile by id")
    void should_find_RestaurantProfile_by_id() {

        long id = Instancio.create(Long.class);
        RestaurantProfileEntity entity = Instancio.create(RestaurantProfileEntity.class);
        RestaurantProfile domain = Instancio.create(RestaurantProfile.class);

        when(restaurantProfileRepository.findById(id)).thenReturn(Optional.of(entity));
        when(restaurantProfileEntityToDomainConverter.convert(entity)).thenReturn(domain);

        RestaurantProfile result = restaurantProfileJpaGateway.findById(id);

        assertEquals(domain, result);
        verify(restaurantProfileRepository).findById(id);
        verify(restaurantProfileEntityToDomainConverter).convert(entity);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when RestaurantProfile is not found by id")
    void should_throw_ResourceNotFoundException_when_RestaurantProfile_is_not_found_by_id() {

        long id = Instancio.create(Long.class);

        when(restaurantProfileRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restaurantProfileJpaGateway.findById(id));
        verify(restaurantProfileRepository).findById(id);
    }

    @Test
    @DisplayName("Should find RestaurantProfile by user email")
    void should_find_RestaurantProfile_by_user_email() {

        String email = Instancio.create(String.class);
        RestaurantProfileEntity entity = Instancio.create(RestaurantProfileEntity.class);
        RestaurantProfile domain = Instancio.create(RestaurantProfile.class);

        when(restaurantProfileRepository.findByUserEntityEmail(email)).thenReturn(Optional.of(entity));
        when(restaurantProfileEntityToDomainConverter.convert(entity)).thenReturn(domain);

        RestaurantProfile result = restaurantProfileJpaGateway.findByUserEntityEmail(email);

        assertEquals(domain, result);
        verify(restaurantProfileRepository).findByUserEntityEmail(email);
        verify(restaurantProfileEntityToDomainConverter).convert(entity);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when RestaurantProfile is not found by user email")
    void should_throw_ResourceNotFoundException_when_RestaurantProfile_is_not_found_by_user_email() {

        String email = Instancio.create(String.class);

        when(restaurantProfileRepository.findByUserEntityEmail(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restaurantProfileJpaGateway.findByUserEntityEmail(email));
        verify(restaurantProfileRepository).findByUserEntityEmail(email);
    }

    @Test
    @DisplayName("Should find RestaurantProfile by business name")
    void should_find_RestaurantProfile_by_business_name() {

        String businessName = Instancio.create(String.class);
        RestaurantProfileEntity entity = Instancio.create(RestaurantProfileEntity.class);
        RestaurantProfile domain = Instancio.create(RestaurantProfile.class);

        when(restaurantProfileRepository.findByBusinessName(businessName)).thenReturn(Optional.of(entity));
        when(restaurantProfileEntityToDomainConverter.convert(entity)).thenReturn(domain);

        RestaurantProfile result = restaurantProfileJpaGateway.findByBusinessName(businessName);

        assertEquals(domain, result);
        verify(restaurantProfileRepository).findByBusinessName(businessName);
        verify(restaurantProfileEntityToDomainConverter).convert(entity);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when RestaurantProfile is not found by business name")
    void should_throw_ResourceNotFoundException_when_RestaurantProfile_is_not_found_by_business_name() {

        String businessName = Instancio.create(String.class);

        when(restaurantProfileRepository.findByBusinessName(businessName)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restaurantProfileJpaGateway.findByBusinessName(businessName));
        verify(restaurantProfileRepository).findByBusinessName(businessName);
    }

    @Test
    @DisplayName("Should save RestaurantProfile")
    void should_save_RestaurantProfile() {

        RestaurantProfile domain = Instancio.create(RestaurantProfile.class);
        RestaurantProfileEntity entity = Instancio.create(RestaurantProfileEntity.class);

        when(restaurantProfileDomainToEntityConverter.convert(domain)).thenReturn(entity);

        restaurantProfileJpaGateway.save(domain);

        verify(restaurantProfileDomainToEntityConverter).convert(domain);
        verify(restaurantProfileRepository).save(entity);
    }

}