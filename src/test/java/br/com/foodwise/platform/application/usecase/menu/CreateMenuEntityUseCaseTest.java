package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.gateway.database.jpa.entities.MenuEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.RestaurantProfileEntity;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.database.jpa.repository.MenuRepository;
import br.com.foodwise.platform.gateway.database.jpa.repository.RestaurantProfileRepository;
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
class CreateMenuEntityUseCaseTest {

//    @InjectMocks
//    private CreateMenuUseCase createMenuUseCase;
//
//    @Mock
//    private MenuRepository menuRepository;
//
//    @Mock
//    private RestaurantProfileRepository restaurantProfileRepository;
//
//    @Mock
//    private SecurityContext securityContext;
//
//    @Mock
//    private Authentication authentication;
//
//    private MenuEntity menuEntity;
//    private UserEntity userEntity;
//    private RestaurantProfileEntity restaurantProfileEntity;
//
//    @BeforeEach
//    void setUp() {
//
//        menuEntity = buildMenu();
//        userEntity = buildMockUser("email@teste.com", "password", UserType.CUSTOMER);
//        restaurantProfileEntity = buildRestaurantProfile();
//
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//    }
//
//    @Test
//    void shouldCreateMenuSuccessfully() {
//        when(authentication.getPrincipal()).thenReturn(userEntity);
//        when(restaurantProfileRepository.findById(any())).thenReturn(Optional.of(restaurantProfileEntity));
//        when(menuRepository.save(any(MenuEntity.class))).thenReturn(menuEntity);
//
//        MenuEntity createdMenuEntity = createMenuUseCase.execute(menuEntity);
//
//        assertNotNull(createdMenuEntity);
//        assertEquals(menuEntity.getId(), createdMenuEntity.getId());
//        assertEquals(menuEntity.getName(), createdMenuEntity.getName());
//        verify(menuRepository, times(1)).save(menuEntity);
//    }
//
//    @Test
//    void shouldThrowExceptionWhenRestaurantDoesNotExist() {
//        when(authentication.getPrincipal()).thenReturn(userEntity);
//        when(restaurantProfileRepository.findById(any())).thenReturn(Optional.empty());
//
//        var exception = assertThrows(ResourceNotFoundException.class,
//                () -> createMenuUseCase.execute(menuEntity));
//
//        assertEquals("RESTAURANT_DOES_NOT_EXIST", exception.getCode());
//        verify(menuRepository, never()).save(any());
//    }

}
