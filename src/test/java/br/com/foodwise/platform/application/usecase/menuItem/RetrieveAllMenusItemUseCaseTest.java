package br.com.foodwise.platform.application.usecase.menuItem;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.MenuItemGateway;
import org.instancio.Instancio;
import org.instancio.InstancioApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RetrieveAllMenusItemUseCaseTest {

    @Mock
    private MenuItemGateway menuItemGateway;

    @InjectMocks
    private RetrieveAllMenusItemUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return all MenuItems from the gateway")
    void should_return_all_MenuItems_from_the_gateway() {

        InstancioApi<List<MenuItem>> listApi = Instancio.ofList(MenuItem.class);
        List<MenuItem> expectedMenuItems = listApi.create();

        when(menuItemGateway.findAll()).thenReturn(expectedMenuItems);

        List<MenuItem> actualMenuItems = useCase.execute();

        assertEquals(expectedMenuItems, actualMenuItems);
        verify(menuItemGateway).findAll();
    }

    @Test
    @DisplayName("Should return an empty list when no MenuItems are found")
    void should_return_an_empty_list_when_no_MenuItems_are_found() {

        List<MenuItem> expectedMenuItems = List.of();

        when(menuItemGateway.findAll()).thenReturn(expectedMenuItems);

        List<MenuItem> actualMenuItems = useCase.execute();

        assertEquals(expectedMenuItems, actualMenuItems);
        verify(menuItemGateway).findAll();
    }

}