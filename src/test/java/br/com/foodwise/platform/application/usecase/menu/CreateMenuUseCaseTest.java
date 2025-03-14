package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.MenuGateway;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static br.com.foodwise.platform.factory.DomainFactory.buildMenu;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateMenuUseCaseTest {

    @InjectMocks
    private CreateMenuUseCase createMenuUseCase;

    @Mock
    private MenuGateway menuGateway;

    @Mock
    private RestaurantProfileGateway restaurantProfileGateway;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    private Menu menu;
    private User user;
    private UserEntity userEntity;
    private RestaurantProfile restaurantProfile;

    @BeforeEach
    void setUp() {

        menu = buildMenu();
        user = Instancio.create(User.class);
        userEntity = Instancio.create(UserEntity.class);
        restaurantProfile = Instancio.create(RestaurantProfile.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void shouldCreateMenuSuccessfully() {
        when(authentication.getPrincipal()).thenReturn(user);
        when(restaurantProfileGateway.findById(anyLong())).thenReturn(restaurantProfile);
        when(menuGateway.save(any(Menu.class))).thenReturn(menu);
        when(authentication.getPrincipal()).thenReturn(userEntity);

        Menu createdMenu = createMenuUseCase.execute(menu);

        assertNotNull(createdMenu);
        assertEquals(menu.getId(), createdMenu.getId());
        assertEquals(menu.getName(), createdMenu.getName());
        verify(menuGateway, times(1)).save(any());
    }

}
