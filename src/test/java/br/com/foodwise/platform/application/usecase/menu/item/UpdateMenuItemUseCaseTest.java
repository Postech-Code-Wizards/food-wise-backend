package br.com.foodwise.platform.application.usecase.menu.item;

import br.com.foodwise.platform.domain.MenuItem;
import br.com.foodwise.platform.gateway.MenuItemGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateMenuItemUseCaseTest {

    @Mock
    private MenuItemGateway menuItemGateway;

    @InjectMocks
    private UpdateMenuItemUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should update a MenuItem and return the updated MenuItem")
    void should_update_a_MenuItem_and_return_the_updated_MenuItem() {

        MenuItem menuItem = Instancio.create(MenuItem.class);
        when(menuItemGateway.save(menuItem)).thenReturn(menuItem);

        MenuItem updatedMenuItem = useCase.execute(menuItem);

        assertEquals(menuItem, updatedMenuItem);
        verify(menuItemGateway).save(menuItem);
    }

    @Test
    @DisplayName("Should call the MenuItemGateway save method with the provided MenuItem")
    void should_call_the_MenuItemGateway_save_method_with_the_provided_MenuItem() {
        MenuItem menuItem = Instancio.create(MenuItem.class);
        when(menuItemGateway.save(menuItem)).thenReturn(menuItem);

        useCase.execute(menuItem);

        verify(menuItemGateway).save(menuItem);
    }

}