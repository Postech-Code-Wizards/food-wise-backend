package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.domain.repository.MenuItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateMenuItemUseCaseTest {

    @InjectMocks
    private UpdateMenuItemUseCase updateMenuItemUseCase;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Test
    void shouldUpdateMenuItemSuccessfully() {
        MenuItem updatedMenuItem = new MenuItem();
        updatedMenuItem.setId(1L);
        updatedMenuItem.setName("Updated Menu Item Name");

        when(menuItemRepository.save(updatedMenuItem)).thenReturn(updatedMenuItem);

        MenuItem result = updateMenuItemUseCase.execute(updatedMenuItem);

        assertNotNull(result);
        assertEquals("Updated Menu Item Name", result.getName());
        assertEquals(1L, result.getId());
        verify(menuItemRepository, times(1)).save(updatedMenuItem);
    }

    @Test
    void shouldThrowExceptionWhenMenuItemIdDoesNotExist() {
        MenuItem nonExistentMenuItem = new MenuItem();
        nonExistentMenuItem.setId(99L);
        nonExistentMenuItem.setName("Non-existent Menu Item");

        when(menuItemRepository.save(nonExistentMenuItem)).thenThrow(new RuntimeException("Menu Item not found"));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> updateMenuItemUseCase.execute(nonExistentMenuItem));

        assertEquals("Menu Item not found", exception.getMessage());
        verify(menuItemRepository, times(1)).save(nonExistentMenuItem);
    }
}
