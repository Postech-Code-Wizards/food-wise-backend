package br.com.foodwise.platform.application.usecase.menuItem;

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

class CreateMenuItemUseCaseTest {

    @Mock
    private MenuItemGateway menuItemGateway;

    @InjectMocks
    private CreateMenuItemUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save a MenuItem and return the saved MenuItem")
    void should_save_a_MenuItem_and_return_the_saved_MenuItem() {

        MenuItem menuItem = Instancio.create(MenuItem.class);
        when(menuItemGateway.save(menuItem)).thenReturn(menuItem);

        MenuItem savedMenuItem = useCase.execute(menuItem);

        assertEquals(menuItem, savedMenuItem);
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