package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.gateway.MenuGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateMenuUseCaseTest {

    Menu updatedMenu;
    @InjectMocks
    private UpdateMenuUseCase updateMenuUseCase;
    @Mock
    private MenuGateway menuGateway;

    @BeforeEach
    void setUp() {
        updatedMenu = Instancio.create(Menu.class);
    }

    @Test
    void shouldUpdateMenuSuccessfully() {

        when(menuGateway.save(updatedMenu)).thenReturn(updatedMenu);

        Menu result = updateMenuUseCase.execute(updatedMenu);

        assertNotNull(result);
        assertEquals(updatedMenu.getName(), result.getName());
        assertEquals(updatedMenu.getId(), result.getId());
        verify(menuGateway, times(1)).save(updatedMenu);
    }

}
