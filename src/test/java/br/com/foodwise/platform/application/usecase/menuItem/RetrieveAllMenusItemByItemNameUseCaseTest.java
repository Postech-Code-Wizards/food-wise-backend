package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.application.usecase.menu.RetrieveAllMenusByRestaurantNameUseCase;
import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.domain.repository.MenuItemRepository;
import br.com.foodwise.platform.domain.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.foodwise.platform.factory.EntityFactory.buildMenu;
import static br.com.foodwise.platform.factory.EntityFactory.buildMenuItem;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RetrieveAllMenusItemByItemNameUseCaseTest {

    @InjectMocks
    private RetrieveAllMenusItemByItemNameUseCase retrieveAllMenusItemByItemNameUseCase;

    @Mock
    private MenuItemRepository menuItemRepository;

    private String itemName;
    private List<MenuItem> menusItem;

    @BeforeEach
    void setUp() {
        itemName = "Test Item";
        menusItem = List.of(buildMenuItem(), buildMenuItem());
    }

    @Test
    void shouldRetrieveAllMenusItemByItemNameSuccessfully() {
        when(menuItemRepository.findMenuItemByName(itemName)).thenReturn(menusItem);

        List<MenuItem> result = retrieveAllMenusItemByItemNameUseCase.execute(itemName);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(menuItemRepository, times(1)).findMenuItemByName(itemName);
    }

    @Test
    void shouldReturnEmptyListWhenNoMenusItemFound() {
        when(menuItemRepository.findMenuItemByName(itemName)).thenReturn(emptyList());

        List<MenuItem> result = retrieveAllMenusItemByItemNameUseCase.execute(itemName);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(menuItemRepository, times(1)).findMenuItemByName(itemName);
    }
}
