package br.com.foodwise.platform.application.usecase.menu.item;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.MenuItemGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RetrieveAllMenusItemByItemNameUseCaseTest {

    @Mock
    private MenuItemGateway menuItemGateway;

    @InjectMocks
    private RetrieveAllMenusItemByItemNameUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_shouldReturnMenuItems() {

        MenuItem menuItem1 = Instancio.create(MenuItem.class);
        MenuItem menuItem2 = Instancio.create(MenuItem.class);
        List<MenuItem> expectedMenuItems = Arrays.asList(menuItem1, menuItem2);

        when(menuItemGateway.findMenuItemByName(anyString())).thenReturn(expectedMenuItems);

        List<MenuItem> actualMenuItems = useCase.execute(anyString());

        assertEquals(expectedMenuItems, actualMenuItems);
        verify(menuItemGateway).findMenuItemByName(anyString());
    }

    @Test
    void testExecute_shouldReturnEmptyList() {
        List<MenuItem> expectedMenuItems = List.of();

        when(menuItemGateway.findMenuItemByName(anyString())).thenReturn(expectedMenuItems);

        List<MenuItem> actualMenuItems = useCase.execute(anyString());

        assertEquals(expectedMenuItems, actualMenuItems);
        verify(menuItemGateway).findMenuItemByName(anyString());
    }

}