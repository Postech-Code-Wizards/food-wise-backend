package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.entities.Menu;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveMenuUseCaseTest {

    @InjectMocks
    private RetrieveMenuUseCase retrieveMenuUseCase;

    @Mock
    private MenuRepository menuRepository;

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = buildMenu();
    }

    @Test
    void shouldRetrieveMenuSuccessfully() {
        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));

        Menu retrievedMenu = retrieveMenuUseCase.execute(1L);

        assertNotNull(retrievedMenu);
        assertEquals(menu.getId(), retrievedMenu.getId());
        assertEquals(menu.getName(), retrievedMenu.getName());
        verify(menuRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenMenuDoesNotExist() {
        when(menuRepository.findById(1L)).thenReturn(Optional.empty());

        var exception = assertThrows(ResourceNotFoundException.class,
                () -> retrieveMenuUseCase.execute(1L));

        assertEquals("MENU_DOES_NOT_EXIST", exception.getCode());
        verify(menuRepository, times(1)).findById(1L);
    }
}
