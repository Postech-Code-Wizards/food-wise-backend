package br.com.foodwise.platform.application.usecase.menu;

import br.com.foodwise.platform.domain.entities.Menu;
import br.com.foodwise.platform.domain.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.foodwise.platform.factory.EntityFactory.buildMenu;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveAllMenusByRestaurantNameUseCaseTest {

    @InjectMocks
    private RetrieveAllMenusByRestaurantNameUseCase retrieveAllMenusByRestaurantNameUseCase;

    @Mock
    private MenuRepository menuRepository;

    private String businessName;
    private List<Menu> menus;

    @BeforeEach
    void setUp() {
        businessName = "Test Restaurant";
        menus = List.of(buildMenu(), buildMenu());
    }

    @Test
    void shouldRetrieveAllMenusByRestaurantNameSuccessfully() {
        when(menuRepository.findByRestaurantProfileBusinessName(businessName)).thenReturn(menus);

        List<Menu> result = retrieveAllMenusByRestaurantNameUseCase.execute(businessName);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(menuRepository, times(1)).findByRestaurantProfileBusinessName(businessName);
    }

    @Test
    void shouldReturnEmptyListWhenNoMenusFound() {
        when(menuRepository.findByRestaurantProfileBusinessName(businessName)).thenReturn(emptyList());

        List<Menu> result = retrieveAllMenusByRestaurantNameUseCase.execute(businessName);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(menuRepository, times(1)).findByRestaurantProfileBusinessName(businessName);
    }
}
