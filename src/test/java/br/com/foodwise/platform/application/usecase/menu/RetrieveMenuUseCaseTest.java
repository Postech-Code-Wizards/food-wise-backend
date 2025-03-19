package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.gateway.MenuGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.foodwise.platform.factory.DomainFactory.buildMenu;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveMenuUseCaseTest {

    @InjectMocks
    private RetrieveMenuUseCase retrieveMenuUseCase;

    @Mock
    private MenuGateway menuGateway;

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = buildMenu();
    }

    @Test
    void shouldRetrieveMenuSuccessfully() {
        when(menuGateway.findById(1L)).thenReturn(menu);

        Menu retrievedMenu = retrieveMenuUseCase.execute(1L);

        assertNotNull(retrievedMenu);
        assertEquals(menu.getId(), retrievedMenu.getId());
        assertEquals(menu.getName(), retrievedMenu.getName());
        verify(menuGateway, times(1)).findById(1L);
    }

}
