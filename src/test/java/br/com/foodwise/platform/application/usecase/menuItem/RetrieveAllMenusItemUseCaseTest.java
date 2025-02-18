package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.domain.repository.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.foodwise.platform.factory.EntityFactory.buildMenuItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetrieveAllMenusItemUseCaseTest {

    @InjectMocks
    private RetrieveAllMenusItemUseCase retrieveAllMenusItemUseCase;

    @Mock
    private MenuItemRepository menuItemRepository;

    private MenuItem menuItem1;
    private MenuItem menuItem2;

    @BeforeEach
    void setUp() {
        menuItem1 = buildMenuItem();

        menuItem2 = buildMenuItem();
    }

    @Test
    void shouldRetrieveAllMenusItemSuccessfully() {
        List<MenuItem> menusItem = List.of(menuItem1, menuItem2);

        when(menuItemRepository.findAll()).thenReturn(menusItem);

        List<MenuItem> retrievedMenusItem = retrieveAllMenusItemUseCase.execute();

        assertNotNull(retrievedMenusItem);
        assertEquals(2, retrievedMenusItem.size());
        assertEquals(menuItem1.getId(), retrievedMenusItem.get(0).getId());
        assertEquals(menuItem2.getId(), retrievedMenusItem.get(1).getId());
        verify(menuItemRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoMenusItemExist() {
        when(menuItemRepository.findAll()).thenReturn(List.of());

        List<MenuItem> retrievedMenusItem = retrieveAllMenusItemUseCase.execute();

        assertNotNull(retrievedMenusItem);
        assertTrue(retrievedMenusItem.isEmpty());
        verify(menuItemRepository, times(1)).findAll();
    }

}
