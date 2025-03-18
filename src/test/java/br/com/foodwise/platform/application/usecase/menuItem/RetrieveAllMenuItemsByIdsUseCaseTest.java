package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.MenuItemGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveAllMenuItemsByIdsUseCaseTest {

    @Mock
    private MenuItemGateway menuItemGateway;

    @InjectMocks
    private RetrieveAllMenuItemsByIdsUseCase useCase;

    @Test
    @DisplayName("Should retrieve all menu items by ids successfully")
    void retrieveAllById_ShouldRetrieveMenuItems() {

        List<Long> menuItemIds = Instancio.ofList(Long.class).size(3).create();
        List<MenuItem> menuItems = Instancio.ofList(MenuItem.class).size(3).create();

        for (int i = 0; i < menuItemIds.size(); i++) {
            when(menuItemGateway.findById(menuItemIds.get(i))).thenReturn(menuItems.get(i));
        }

        List<MenuItem> result = useCase.retrieveAllById(menuItemIds);

        assertNotNull(result);
        assertEquals(menuItems.size(), result.size());
        assertEquals(menuItems, result);
    }

}