package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.gateway.database.jpa.entities.MenuEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.foodwise.platform.factory.EntityFactory.buildMenu;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveAllMenusUseCaseTest {

//    @InjectMocks
//    private RetrieveAllMenusUseCase retrieveAllMenusUseCase;
//
//    @Mock
//    private MenuRepository menuRepository;
//
//    private MenuEntity menuEntity1;
//    private MenuEntity menuEntity2;
//
//    @BeforeEach
//    void setUp() {
//        menuEntity1 = buildMenu();
//
//        menuEntity2 = buildMenu();
//    }
//
//    @Test
//    void shouldRetrieveAllMenusSuccessfully() {
//        List<MenuEntity> menuEntities = List.of(menuEntity1, menuEntity2);
//
//        when(menuRepository.findAll()).thenReturn(menuEntities);
//
//        List<MenuEntity> retrievedMenuEntities = retrieveAllMenusUseCase.execute();
//
//        assertNotNull(retrievedMenuEntities);
//        assertEquals(2, retrievedMenuEntities.size());
//        assertEquals(menuEntity1.getId(), retrievedMenuEntities.get(0).getId());
//        assertEquals(menuEntity2.getId(), retrievedMenuEntities.get(1).getId());
//        verify(menuRepository, times(1)).findAll();
//    }
//
//    @Test
//    void shouldReturnEmptyListWhenNoMenusExist() {
//        when(menuRepository.findAll()).thenReturn(List.of());
//
//        List<MenuEntity> retrievedMenuEntities = retrieveAllMenusUseCase.execute();
//
//        assertNotNull(retrievedMenuEntities);
//        assertTrue(retrievedMenuEntities.isEmpty());
//        verify(menuRepository, times(1)).findAll();
//    }
}