package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.domain.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.foodwise.platform.factory.EntityFactory.buildMenu;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateMenuUseCaseTest {

    @InjectMocks
    private UpdateMenuUseCase updateMenuUseCase;

    @Mock
    private MenuRepository menuRepository;

    @BeforeEach
    void setUp() {
        var existingMenu = buildMenu();
    }

    @Test
    void shouldUpdateMenuSuccessfully() {
        Menu updatedMenu = new Menu();
        updatedMenu.setId(1L);
        updatedMenu.setName("Updated Menu Name");

        when(menuRepository.save(updatedMenu)).thenReturn(updatedMenu);

        Menu result = updateMenuUseCase.execute(updatedMenu);

        assertNotNull(result);
        assertEquals("Updated Menu Name", result.getName());
        assertEquals(1L, result.getId());
        verify(menuRepository, times(1)).save(updatedMenu);
    }

    @Test
    void shouldThrowExceptionWhenMenuIdDoesNotExist() {
        Menu nonExistentMenu = new Menu();
        nonExistentMenu.setId(99L);
        nonExistentMenu.setName("Non-existent Menu");

        when(menuRepository.save(nonExistentMenu)).thenThrow(new RuntimeException("Menu not found"));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> updateMenuUseCase.execute(nonExistentMenu));

        assertEquals("Menu not found", exception.getMessage());
        verify(menuRepository, times(1)).save(nonExistentMenu);
    }
}
