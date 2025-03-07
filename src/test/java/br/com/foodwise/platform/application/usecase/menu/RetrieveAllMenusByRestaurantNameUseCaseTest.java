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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetrieveAllMenusByRestaurantNameUseCaseTest {

    @InjectMocks
    private RetrieveAllMenusByRestaurantNameUseCase retrieveAllMenusByRestaurantNameUseCase;

    @Mock
    private MenuGateway menuGateway;

    private String businessName;
    private List<Menu> menus;

    @BeforeEach
    void setUp() {
        businessName = "Test Restaurant";
        menus = List.of(buildMenu(), buildMenu());
    }

    @Test
    void shouldRetrieveAllMenusByRestaurantNameSuccessfully() {
        when(menuGateway.findByRestaurantProfileEntityBusinessName(anyString())).thenReturn(menus);

        List<Menu> result = retrieveAllMenusByRestaurantNameUseCase.execute(businessName);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(menuGateway, times(1)).findByRestaurantProfileEntityBusinessName(businessName);
    }

}
