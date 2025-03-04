package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.gateway.database.jpa.entities.MenuEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.MenuRepository;
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
class UpdateMenuEntityUseCaseTest {

//    @InjectMocks
//    private UpdateMenuUseCase updateMenuUseCase;
//
//    @Mock
//    private MenuRepository menuRepository;
//
//    @BeforeEach
//    void setUp() {
//        var existingMenu = buildMenu();
//    }
//
//    @Test
//    void shouldUpdateMenuSuccessfully() {
//        MenuEntity updatedMenuEntity = new MenuEntity();
//        updatedMenuEntity.setId(1L);
//        updatedMenuEntity.setName("Updated Menu Name");
//
//        when(menuRepository.save(updatedMenuEntity)).thenReturn(updatedMenuEntity);
//
//        MenuEntity result = updateMenuUseCase.execute(updatedMenuEntity);
//
//        assertNotNull(result);
//        assertEquals("Updated Menu Name", result.getName());
//        assertEquals(1L, result.getId());
//        verify(menuRepository, times(1)).save(updatedMenuEntity);
//    }
//
//    @Test
//    void shouldThrowExceptionWhenMenuIdDoesNotExist() {
//        MenuEntity nonExistentMenuEntity = new MenuEntity();
//        nonExistentMenuEntity.setId(99L);
//        nonExistentMenuEntity.setName("Non-existent Menu");
//
//        when(menuRepository.save(nonExistentMenuEntity)).thenThrow(new RuntimeException("Menu not found"));
//
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> updateMenuUseCase.execute(nonExistentMenuEntity));
//
//        assertEquals("Menu not found", exception.getMessage());
//        verify(menuRepository, times(1)).save(nonExistentMenuEntity);
//    }
}
