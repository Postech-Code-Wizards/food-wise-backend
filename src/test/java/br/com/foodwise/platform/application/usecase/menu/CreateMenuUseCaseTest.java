package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.domain.entities.RestaurantProfile;
import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.domain.repository.MenuRepository;
import br.com.foodwise.platform.domain.repository.RestaurantProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static br.com.foodwise.platform.factory.EntityFactory.buildMenu;
import static br.com.foodwise.platform.factory.EntityFactory.buildRestaurantProfile;
import static br.com.foodwise.platform.factory.SecurityHelperFactory.buildMockUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateMenuUseCaseTest {

    @InjectMocks
    private CreateMenuUseCase createMenuUseCase;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private RestaurantProfileRepository restaurantProfileRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    private Menu menu;
    private User user;
    private RestaurantProfile restaurantProfile;

    @BeforeEach
    void setUp() {

        menu = buildMenu();
        user = buildMockUser("email@teste.com", "password", UserType.CUSTOMER);
        restaurantProfile = buildRestaurantProfile();

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void shouldCreateMenuSuccessfully() {
        when(authentication.getPrincipal()).thenReturn(user);
        when(restaurantProfileRepository.findById(any())).thenReturn(Optional.of(restaurantProfile));
        when(menuRepository.save(any(Menu.class))).thenReturn(menu);

        Menu createdMenu = createMenuUseCase.execute(menu);

        assertNotNull(createdMenu);
        assertEquals(menu.getId(), createdMenu.getId());
        assertEquals(menu.getName(), createdMenu.getName());
        verify(menuRepository, times(1)).save(menu);
    }

    @Test
    void shouldThrowExceptionWhenRestaurantDoesNotExist() {
        when(authentication.getPrincipal()).thenReturn(user);
        when(restaurantProfileRepository.findById(any())).thenReturn(Optional.empty());

        var exception = assertThrows(ResourceNotFoundException.class,
                () -> createMenuUseCase.execute(menu));

        assertEquals("RESTAURANT_DOES_NOT_EXIST", exception.getCode());
        verify(menuRepository, never()).save(any());
    }

}
