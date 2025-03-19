package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.gateway.MenuGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.foodwise.platform.factory.DomainFactory.buildMenu;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveAllMenusUseCaseTest {

    @InjectMocks
    private RetrieveAllMenusUseCase retrieveAllMenusUseCase;

    @Mock
    private MenuGateway menuGateway;

    private Menu menu1;
    private Menu menu2;

    @BeforeEach
    void setUp() {
        menu1 = buildMenu();
        menu2 = buildMenu();
    }

    @Test
    void shouldRetrieveAllMenusSuccessfully() {
        List<Menu> menuEntities = List.of(menu1, menu2);

        when(menuGateway.findAll()).thenReturn(menuEntities);

        List<Menu> retrievedMenuEntities = retrieveAllMenusUseCase.execute();

        assertNotNull(retrievedMenuEntities);
        assertEquals(2, retrievedMenuEntities.size());
        assertEquals(menu1.getId(), retrievedMenuEntities.get(0).getId());
        assertEquals(menu2.getId(), retrievedMenuEntities.get(1).getId());
        verify(menuGateway, times(1)).findAll();
    }

}