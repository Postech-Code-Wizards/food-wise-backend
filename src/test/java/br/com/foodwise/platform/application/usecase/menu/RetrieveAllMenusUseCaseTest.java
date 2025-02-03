package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.domain.repository.MenuRepository;
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

    @InjectMocks
    private RetrieveAllMenusUseCase retrieveAllMenusUseCase;

    @Mock
    private MenuRepository menuRepository;

    private Menu menu1;
    private Menu menu2;

    @BeforeEach
    void setUp() {
        menu1 = buildMenu();

        menu2 = buildMenu();
    }

    @Test
    void shouldRetrieveAllMenusSuccessfully() {
        List<Menu> menus = List.of(menu1, menu2);

        when(menuRepository.findAll()).thenReturn(menus);

        List<Menu> retrievedMenus = retrieveAllMenusUseCase.execute();

        assertNotNull(retrievedMenus);
        assertEquals(2, retrievedMenus.size());
        assertEquals(menu1.getId(), retrievedMenus.get(0).getId());
        assertEquals(menu2.getId(), retrievedMenus.get(1).getId());
        verify(menuRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoMenusExist() {
        when(menuRepository.findAll()).thenReturn(List.of());

        List<Menu> retrievedMenus = retrieveAllMenusUseCase.execute();

        assertNotNull(retrievedMenus);
        assertTrue(retrievedMenus.isEmpty());
        verify(menuRepository, times(1)).findAll();
    }
}