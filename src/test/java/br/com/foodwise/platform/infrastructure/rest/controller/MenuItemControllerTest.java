package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.facade.MenuItemFacade;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemAvailable;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuItem.RegisterMenuItemRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuItemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
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

import static br.com.foodwise.platform.factory.SecurityHelperFactory.authenticateUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class MenuItemControllerTest {

    private static final String TEST_EMAIL = "test@code-wizards.com";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MenuItemFacade menuItemFacade;

    @BeforeEach
    void setUp() {
        authenticateUser(TEST_EMAIL, "testPassword", UserType.RESTAURANT_OWNER);
    }

    @Nested
    class CreateMenuItemContext {

        @Test
        @DisplayName("Should create a MenuItem and return CREATED response")
        void should_create_a_MenuItem_and_return_CREATED_response() throws Exception {
            var request = Instancio.create(RegisterMenuItemRequest.class);
            var response = Instancio.create(MenuItemResponse.class);
            given(menuItemFacade.createMenuItem(any(RegisterMenuItemRequest.class))).willReturn(response);

            mockMvc.perform(post("/api/v1/menu-item")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));

            verify(menuItemFacade, times(1)).createMenuItem(any(RegisterMenuItemRequest.class));
        }
    }

    @Nested
    class GetMenuItemContext {

        @Test
        @DisplayName("Should get a MenuItem by id and return OK response")
        void should_get_a_MenuItem_by_id_and_return_OK_response() throws Exception {
            var id = Instancio.create(Long.class);
            var response = Instancio.create(MenuItemResponse.class);
            given(menuItemFacade.getMenuItemById(id)).willReturn(response);

            mockMvc.perform(get("/api/v1/menu-item/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));

            verify(menuItemFacade, times(1)).getMenuItemById(id);
        }

        @Test
        @DisplayName("Should get MenuItems by item name and return OK response")
        void should_get_MenuItems_by_item_name_and_return_OK_response() throws Exception {
            var name = Instancio.create(String.class);
            var response = Instancio.ofList(MenuItemResponse.class).create();
            given(menuItemFacade.getAllMenusItemByItemName(name)).willReturn(response);

            mockMvc.perform(get("/api/v1/menu-item/item/{name}", name))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));

            verify(menuItemFacade, times(1)).getAllMenusItemByItemName(name);
        }

        @Test
        @DisplayName("Should get all MenuItems and return OK response")
        void should_get_all_MenuItems_and_return_OK_response() throws Exception {
            var response = Instancio.ofList(MenuItemResponse.class).create();
            given(menuItemFacade.getAllMenuItems()).willReturn(response);

            mockMvc.perform(get("/api/v1/menu-item"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));

            verify(menuItemFacade, times(1)).getAllMenuItems();
        }
    }

    @Nested
    class UpdateMenuItemContext {

        @Test
        @DisplayName("Should update a MenuItem and return OK response")
        void should_update_a_MenuItem_and_return_OK_response() throws Exception {
            var id = Instancio.create(Long.class);
            var request = Instancio.create(RegisterMenuItemRequest.class);
            var response = Instancio.create(MenuItemResponse.class);
            given(menuItemFacade.updateMenuItem(id, request)).willReturn(response);

            mockMvc.perform(put("/api/v1/menu-item/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk());

            verify(menuItemFacade, times(1)).updateMenuItem(any(Long.class), any(RegisterMenuItemRequest.class));
        }

        @Test
        @DisplayName("Should update available MenuItem and return OK response")
        void should_update_available_MenuItem_and_return_OK_response() throws Exception {
            var id = Instancio.create(Long.class);
            var available = Instancio.create(RegisterMenuItemAvailable.class);
            var response = Instancio.create(MenuItemResponse.class);
            given(menuItemFacade.updateAvailableMenuItem(id, available)).willReturn(response);

            mockMvc.perform(put("/api/v1/menu-item/available/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(available)))
                    .andExpect(status().isOk());

            verify(menuItemFacade, times(1)).updateAvailableMenuItem(any(Long.class), any(RegisterMenuItemAvailable.class));
        }
    }
}
