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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteMenuUseCaseTest {

    @InjectMocks
    private DeleteMenuUseCase deleteMenuUseCase;

    @Mock
    private MenuRepository menuRepository;

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = buildMenu();
    }

    @Test
    void shouldDeleteMenuSuccessfully() {
        when(menuRepository.findById(menu.getId())).thenReturn(Optional.of(menu));

        deleteMenuUseCase.execute(menu.getId());

        verify(menuRepository, times(1)).findById(menu.getId());
        verify(menuRepository, times(1)).delete(menu);
    }

    @Test
    void shouldThrowExceptionWhenMenuDoesNotExist() {
        when(menuRepository.findById(menu.getId())).thenReturn(Optional.empty());

        var exception = assertThrows(ResourceNotFoundException.class,
                () -> deleteMenuUseCase.execute(menu.getId()));

        assertEquals("MENU_DOES_NOT_EXIST", exception.getCode());
        verify(menuRepository, never()).delete(any());
    }
}
