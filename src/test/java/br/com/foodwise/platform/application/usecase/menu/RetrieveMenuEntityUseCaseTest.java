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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveMenuEntityUseCaseTest {

//    @InjectMocks
//    private RetrieveMenuUseCase retrieveMenuUseCase;
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
//    void shouldRetrieveMenuSuccessfully() {
//        when(menuRepository.findById(1L)).thenReturn(Optional.of(menuEntity));
//
//        MenuEntity retrievedMenuEntity = retrieveMenuUseCase.execute(1L);
//
//        assertNotNull(retrievedMenuEntity);
//        assertEquals(menuEntity.getId(), retrievedMenuEntity.getId());
//        assertEquals(menuEntity.getName(), retrievedMenuEntity.getName());
//        verify(menuRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void shouldThrowResourceNotFoundExceptionWhenMenuDoesNotExist() {
//        when(menuRepository.findById(1L)).thenReturn(Optional.empty());
//
//        var exception = assertThrows(ResourceNotFoundException.class,
//                () -> retrieveMenuUseCase.execute(1L));
//
//        assertEquals("MENU_DOES_NOT_EXIST", exception.getCode());
//        verify(menuRepository, times(1)).findById(1L);
//    }
}
