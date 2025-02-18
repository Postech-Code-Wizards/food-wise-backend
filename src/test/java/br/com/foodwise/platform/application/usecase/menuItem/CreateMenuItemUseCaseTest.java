package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.domain.repository.MenuItemRepository;
import br.com.foodwise.platform.domain.repository.MenuRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.foodwise.platform.factory.EntityFactory.buildMenu;
import static br.com.foodwise.platform.factory.EntityFactory.buildMenuItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateMenuItemUseCaseTest {

    @InjectMocks
    private CreateMenuItemUseCase createMenuItemUseCase;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuRepository menuRepository;

    private MenuItem menuItem;

    private Menu menu;

    @BeforeEach
    void setUp() {
        menuItem = buildMenuItem();
        menu = buildMenu();
    }

    @Test
    void shouldCreateMenuItemSuccessfully() {
        when(menuRepository.findById(any())).thenReturn(Optional.of(menu));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);

        MenuItem createdMenuItem = createMenuItemUseCase.execute(menuItem);

        assertNotNull(createdMenuItem);
        assertEquals(menuItem.getId(), createdMenuItem.getId());
        assertEquals(menuItem.getName(), createdMenuItem.getName());
        verify(menuItemRepository, times(1)).save(menuItem);
    }

    @Test
    void shouldThrowExceptionWhenMenuItemNotFound() {
        when(menuRepository.findById(any())).thenReturn(Optional.empty());

        var exception = assertThrows(ResourceNotFoundException.class,
                () -> createMenuItemUseCase.execute(menuItem));

        assertEquals("NOT_FOUND", exception.getCode());
        verify(menuItemRepository, never()).save(any());
    }
}
