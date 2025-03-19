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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteMenuUseCaseTest {

    @InjectMocks
    private DeleteMenuUseCase deleteMenuUseCase;

    @Mock
    private MenuGateway menuGateway;

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = buildMenu();
    }

    @Test
    void shouldDeleteMenuSuccessfully() {
        when(menuGateway.findById(menu.getId())).thenReturn(menu);

        deleteMenuUseCase.execute(menu.getId());

        verify(menuGateway, times(1)).findById(menu.getId());
        verify(menuGateway, times(1)).delete(menu);
    }

}
