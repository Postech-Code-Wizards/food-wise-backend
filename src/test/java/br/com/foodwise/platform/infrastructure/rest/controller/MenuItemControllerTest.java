package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.service.MenuItemService;
import br.com.foodwise.platform.domain.entities.MenuItem;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.converter.menuItem.MenuItemToMenuItemResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuItemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static br.com.foodwise.platform.factory.EntityFactory.buildMenuItem;
import static br.com.foodwise.platform.factory.RequestFactory.buildRegisterMenuItemRequest;
import static br.com.foodwise.platform.factory.ResponseFactory.buildMenuItemResponse;
import static br.com.foodwise.platform.factory.SecurityHelperFactory.authenticateUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class MenuItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MenuItemService menuItemService;

    @MockBean
    private MenuItemToMenuItemResponseConverter menuItemToMenuItemResponseConverter;

    @Nested
    class AuthenticatedEndpointsContext {

        private static final String TEST_EMAIL = "test@code-wizards.com";

        @BeforeEach
        void setUp() {
            authenticateUser(TEST_EMAIL, "testPassword", UserType.RESTAURANT_OWNER);
        }


        @Test
        @DisplayName("Should create a menu item successfully")
        void shouldCreateMenuItemSuccessfully() throws Exception {
            RegisterMenuItemRequest menuItemRequest = buildRegisterMenuItemRequest();
            MenuItemResponse menuItemResponse = buildMenuItemResponse();

            given(menuItemService.createMenuItem(any(MenuItem.class))).willReturn(buildMenuItem());
            given(menuItemToMenuItemResponseConverter.convert(any(MenuItem.class))).willReturn(menuItemResponse);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/menu-item")
                            .contentType(MediaType.APPLICATION_JSON).content(objectMapper
                                    .writeValueAsString(menuItemRequest)))
                    .andExpect(MockMvcResultMatchers.status().isCreated());

            verify(menuItemService, times(1)).createMenuItem(any());
        }

        @Test
        @DisplayName("Should retrieve menu item by ID successfully")
        void shouldRetrieveMenuItemByIdSuccessfully() throws Exception {
            var menuItemId = 1L;
            var menuItem = buildMenuItem();
            var menuItemResponse = buildMenuItemResponse();

            given(menuItemService.getMenuItemById(menuItemId)).willReturn(menuItem);
            given(menuItemToMenuItemResponseConverter.convert(menuItem)).willReturn(menuItemResponse);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menu-item/{id}", menuItemId))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                            .value(menuItemResponse.getId()));

            verify(menuItemService, times(1)).getMenuItemById(menuItemId);
        }

        @Test
        @DisplayName("Should retrieve all menus items")
        void shouldRetrieveAllMenusItemsSuccessfully() throws Exception {
            var menuItems = buildMenuItem();
            var menuItemResponse = buildMenuItemResponse();

            given(menuItemService.getAllMenuItems()).willReturn(List.of(menuItems));
            given(menuItemToMenuItemResponseConverter.convert(menuItems)).willReturn(menuItemResponse);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menu-item"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id")
                            .value(menuItemResponse.getId()));

            verify(menuItemService, times(1)).getAllMenuItems();
        }

        @Test
        @DisplayName("Should retrieve menus item by item name successfully")
        void shouldRetrieveMenusItemsByItemNameSuccessfully() throws Exception {
            String itemName = "Test Item";
            var menuItem = buildMenuItem();
            var menuItemResponse = buildMenuItemResponse();

            given(menuItemService.getAllMenusItemByItemName(itemName)).willReturn(List.of(menuItem));
            given(menuItemToMenuItemResponseConverter.convert(menuItem)).willReturn(menuItemResponse);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menu-item/item/{name}", itemName))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(menuItemResponse.getId()));

            verify(menuItemService, times(1)).getAllMenusItemByItemName(itemName);
        }
    }
}

