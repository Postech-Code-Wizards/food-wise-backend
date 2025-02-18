package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.application.usecase.menu.RetrieveMenuUseCase;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RetrieveMenuItemUseCaseTest {

    @InjectMocks
    private RetrieveMenuItemUseCase retrieveMenuItemUseCase;

    @Mock
    private MenuItemRepository menuItemRepository;

    private MenuItem menuItem;

    @BeforeEach
    void setUp() {
        menuItem = buildMenuItem();
    }

    @Test
    void shouldRetrieveMenuItemSuccessfully() {
        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(menuItem));

        MenuItem retrievedMenuItem = retrieveMenuItemUseCase.execute(1L);

        assertNotNull(retrievedMenuItem);
        assertEquals(menuItem.getId(), retrievedMenuItem.getId());
        assertEquals(menuItem.getName(), retrievedMenuItem.getName());
        verify(menuItemRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenMenuItemNotFound() {
        when(menuItemRepository.findById(1L)).thenReturn(Optional.empty());

        var exception = assertThrows(ResourceNotFoundException.class,
                () -> retrieveMenuItemUseCase.execute(1L));

        assertEquals("NOT_FOUND", exception.getCode());
        verify(menuItemRepository, times(1)).findById(1L);
    }
}
