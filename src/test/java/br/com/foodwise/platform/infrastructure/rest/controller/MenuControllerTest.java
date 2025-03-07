package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.service.MenuService;
import br.com.foodwise.platform.domain.Menu;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.converter.menu.MenuToMenuResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.menu.MenuUpdateRequestToMenuConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.menu.RegisterMenuRequestToMenuConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static br.com.foodwise.platform.factory.DomainFactory.buildMenu;
import static br.com.foodwise.platform.factory.RequestFactory.buildRegisterMenuRequest;
import static br.com.foodwise.platform.factory.SecurityHelperFactory.authenticateUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class MenuControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MenuService menuService;

    @MockBean
    private RegisterMenuRequestToMenuConverter registerMenuRequestToMenuConverter;

    @MockBean
    private MenuToMenuResponseConverter menuToMenuResponseConverter;

    @MockBean
    private MenuUpdateRequestToMenuConverter menuUpdateRequestToMenuConverter;

    @InjectMocks
    private MenuController menuController;

    @Nested
    class AuthenticatedEndpointsContext {
        private static final String TEST_EMAIL = "test@code-wizards.com";

        @BeforeEach
        void setUp() {
            authenticateUser(TEST_EMAIL, "testPassword", UserType.RESTAURANT_OWNER);
        }

        @Test
        @DisplayName("Should create a menu successfully")
        void shouldCreateMenuSuccessfully() throws Exception {
            RegisterMenuRequest menuRequest = buildRegisterMenuRequest();
            var menuResponse = Instancio.create(MenuResponse.class);

            given(menuService.createMenu(any(Menu.class))).willReturn(buildMenu());
            given(menuToMenuResponseConverter.convert(any(Menu.class))).willReturn(menuResponse);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/menu")
                            .contentType(MediaType.APPLICATION_JSON).content(objectMapper
                                    .writeValueAsString(menuRequest)))
                    .andExpect(MockMvcResultMatchers.status().isCreated());

            verify(menuService, times(1)).createMenu(any());
        }

        @Test
        @DisplayName("Should retrieve menu by ID successfully")
        void shouldRetrieveMenuByIdSuccessfully() throws Exception {
            var menuId = 1L;
            var menu = buildMenu();
            var menuResponse = Instancio.create(MenuResponse.class);

            given(menuService.getMenuById(menuId)).willReturn(menu);
            given(menuToMenuResponseConverter.convert(menu)).willReturn(menuResponse);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menu/{id}", menuId))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                            .value(menuResponse.getId()));

            verify(menuService, times(1)).getMenuById(menuId);
        }

        @Test
        @DisplayName("Should retrieve all menus")
        void shouldRetrieveAllMenusSuccessfully() throws Exception {
            var menu = buildMenu();
            var menuResponse = Instancio.create(MenuResponse.class);

            given(menuService.getAllMenus()).willReturn(List.of(menu));
            given(menuToMenuResponseConverter.convert(menu)).willReturn(menuResponse);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menu"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id")
                            .value(menuResponse.getId()));

            verify(menuService, times(1)).getAllMenus();
        }

        @Test
        @DisplayName("Should update menu successfully")
        void shouldUpdateMenuSuccessfully() throws Exception {
            var menuId = 1L;
            var menuRequest = buildRegisterMenuRequest();
            var updatedMenu = buildMenu();
            var menuResponse = Instancio.create(MenuResponse.class);

            given(menuService.updateMenu(any(Menu.class))).willReturn(updatedMenu);
            given(menuToMenuResponseConverter.convert(updatedMenu)).willReturn(menuResponse);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/menu/{id}", menuId)
                            .contentType(MediaType.APPLICATION_JSON).content(
                                    objectMapper.writeValueAsString(menuRequest)))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            verify(menuService, times(1)).updateMenu(any());
        }

        @Test
        @DisplayName("Should delete menu successfully")
        void shouldDeleteMenuSuccessfully() throws Exception {
            var menuId = 1L;

            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/menu/{id}", menuId))
                    .andExpect(MockMvcResultMatchers.status()
                            .isNoContent());

            verify(menuService, times(1)).deleteMenu(menuId);
        }

        @Test
        @DisplayName("Should retrieve menus by restaurant name successfully")
        void shouldRetrieveMenusByRestaurantNameSuccessfully() throws Exception {
            String restaurantName = "Test Restaurant";
            var menu = buildMenu();
            var menuResponse = Instancio.create(MenuResponse.class);

            given(menuService.getAllMenusByRestaurantName(restaurantName)).willReturn(List.of(menu));
            given(menuToMenuResponseConverter.convert(menu)).willReturn(menuResponse);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menu/restaurant/{name}", restaurantName))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(menuResponse.getId()));

            verify(menuService, times(1)).getAllMenusByRestaurantName(restaurantName);
        }
    }
}

