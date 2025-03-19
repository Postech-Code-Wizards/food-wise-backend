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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RetrieveMenuItemUseCaseTest {

    @Mock
    private MenuItemGateway menuItemGateway;

    @InjectMocks
    private RetrieveMenuItemUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a MenuItem when found by id")
    void should_return_a_MenuItem_when_found_by_id() {

        MenuItem expectedMenuItem = Instancio.create(MenuItem.class);
        Long id = Instancio.create(Long.class);

        when(menuItemGateway.findById(id)).thenReturn(expectedMenuItem);

        MenuItem actualMenuItem = useCase.execute(id);

        assertEquals(expectedMenuItem, actualMenuItem);
        verify(menuItemGateway).findById(id);
    }

    @Test
    @DisplayName("Should return null when MenuItem is not found by id")
    void should_return_null_when_MenuItem_is_not_found_by_id() {

        Long id = Instancio.create(Long.class);

        when(menuItemGateway.findById(id)).thenReturn(null);

        MenuItem actualMenuItem = useCase.execute(id);

        assertNull(actualMenuItem);
        verify(menuItemGateway).findById(id);
    }

}