package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.gateway.database.jpa.entities.MenuEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.MenuRepository;
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
class DeleteMenuEntityUseCaseTest {

//    @InjectMocks
//    private DeleteMenuUseCase deleteMenuUseCase;
//
//    @Mock
//    private MenuRepository menuRepository;
//
//    private MenuEntity menuEntity;
//
//    @BeforeEach
//    void setUp() {
//        menuEntity = buildMenu();
//    }
//
//    @Test
//    void shouldDeleteMenuSuccessfully() {
//        when(menuRepository.findById(menuEntity.getId())).thenReturn(Optional.of(menuEntity));
//
//        deleteMenuUseCase.execute(menuEntity.getId());
//
//        verify(menuRepository, times(1)).findById(menuEntity.getId());
//        verify(menuRepository, times(1)).delete(menuEntity);
//    }
//
//    @Test
//    void shouldThrowExceptionWhenMenuDoesNotExist() {
//        when(menuRepository.findById(menuEntity.getId())).thenReturn(Optional.empty());
//
//        var exception = assertThrows(ResourceNotFoundException.class,
//                () -> deleteMenuUseCase.execute(menuEntity.getId()));
//
//        assertEquals("MENU_DOES_NOT_EXIST", exception.getCode());
//        verify(menuRepository, never()).delete(any());
//    }
}
