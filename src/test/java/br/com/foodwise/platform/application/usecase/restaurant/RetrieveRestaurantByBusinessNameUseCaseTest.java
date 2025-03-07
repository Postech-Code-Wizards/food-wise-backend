package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class RetrieveRestaurantByBusinessNameUseCaseTest {

    @Mock
    private RestaurantProfileGateway restaurantProfileGateway;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private RetrieveRestaurantByBusinessNameUseCase retrieveRestaurantByBusinessNameUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @DisplayName("You must successfully recover restaurant by email")
    void shouldRetrieveRestaurantByEmailSuccessfully() {
        var restaurantProfile = Instancio.create(RestaurantProfile.class);
        var user = Instancio.create(UserEntity.class);

        when(restaurantProfileGateway.findByBusinessName(restaurantProfile.getBusinessName()))
                .thenReturn(restaurantProfile);
        when(authentication.getPrincipal()).thenReturn(user);

        var response = retrieveRestaurantByBusinessNameUseCase.execute(restaurantProfile.getBusinessName());

        assertNotNull(response);
        assertEquals(response, restaurantProfile);
    }

}