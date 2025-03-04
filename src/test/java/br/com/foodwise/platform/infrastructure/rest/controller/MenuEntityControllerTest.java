package br.com.foodwise.platform.infrastructure.rest.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class MenuEntityControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private MenuService menuService;
//
//    @MockBean
//    private RegisterMenuRequestToMenuConverter registerMenuRequestToMenuConverter;
//
//    @MockBean
//    private MenuToMenuResponseConverter menuToMenuResponseConverter;
//
//    @MockBean
//    private MenuUpdateRequestToMenuConverter menuUpdateRequestToMenuConverter;
//
//    @InjectMocks
//    private MenuController menuController;
//
//    @Nested
//    class AuthenticatedEndpointsContext {
//        private static final String TEST_EMAIL = "test@code-wizards.com";
//
//        @BeforeEach
//        void setUp() {
//            authenticateUser(TEST_EMAIL, "testPassword", UserType.RESTAURANT_OWNER);
//        }
//
//        @Test
//        @DisplayName("Should create a menu successfully")
//        void shouldCreateMenuSuccessfully() throws Exception {
//            RegisterMenuRequest menuRequest = buildRegisterMenuRequest();
//            MenuResponse menuResponse = buildMenuResponse();
//
//            given(menuService.createMenu(any(MenuEntity.class))).willReturn(buildMenu());
//            given(menuToMenuResponseConverter.convert(any(MenuEntity.class))).willReturn(menuResponse);
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/menu")
//                            .contentType(MediaType.APPLICATION_JSON).content(objectMapper
//                                    .writeValueAsString(menuRequest)))
//                    .andExpect(MockMvcResultMatchers.status().isCreated());
//
//            verify(menuService, times(1)).createMenu(any());
//        }
//
//        @Test
//        @DisplayName("Should retrieve menu by ID successfully")
//        void shouldRetrieveMenuByIdSuccessfully() throws Exception {
//            var menuId = 1L;
//            var menu = buildMenu();
//            var menuResponse = buildMenuResponse();
//
//            given(menuService.getMenuById(menuId)).willReturn(menu);
//            given(menuToMenuResponseConverter.convert(menu)).willReturn(menuResponse);
//
//            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menu/{id}", menuId))
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.jsonPath("$.id")
//                            .value(menuResponse.getId()));
//
//            verify(menuService, times(1)).getMenuById(menuId);
//        }
//
//        @Test
//        @DisplayName("Should retrieve all menus")
//        void shouldRetrieveAllMenusSuccessfully() throws Exception {
//            var menu = buildMenu();
//            var menuResponse = buildMenuResponse();
//
//            given(menuService.getAllMenus()).willReturn(List.of(menu));
//            given(menuToMenuResponseConverter.convert(menu)).willReturn(menuResponse);
//
//            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menu"))
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id")
//                            .value(menuResponse.getId()));
//
//            verify(menuService, times(1)).getAllMenus();
//        }
//
//        @Test
//        @DisplayName("Should update menu successfully")
//        void shouldUpdateMenuSuccessfully() throws Exception {
//            var menuId = 1L;
//            var menuRequest = buildRegisterMenuRequest();
//            var updatedMenu = buildMenu();
//            var menuResponse = buildMenuResponse();
//
//            given(menuService.updateMenu(any(MenuEntity.class))).willReturn(updatedMenu);
//            given(menuToMenuResponseConverter.convert(updatedMenu)).willReturn(menuResponse);
//
//            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/menu/{id}", menuId)
//                            .contentType(MediaType.APPLICATION_JSON).content(
//                                    objectMapper.writeValueAsString(menuRequest)))
//                    .andExpect(MockMvcResultMatchers.status().isOk());
//
//            verify(menuService, times(1)).updateMenu(any());
//        }
//
//        @Test
//        @DisplayName("Should delete menu successfully")
//        void shouldDeleteMenuSuccessfully() throws Exception {
//            var menuId = 1L;
//
//            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/menu/{id}", menuId))
//                    .andExpect(MockMvcResultMatchers.status()
//                            .isNoContent());
//
//            verify(menuService, times(1)).deleteMenu(menuId);
//        }
//
//        @Test
//        @DisplayName("Should retrieve menus by restaurant name successfully")
//        void shouldRetrieveMenusByRestaurantNameSuccessfully() throws Exception {
//            String restaurantName = "Test Restaurant";
//            var menu = buildMenu();
//            var menuResponse = buildMenuResponse();
//
//            given(menuService.getAllMenusByRestaurantName(restaurantName)).willReturn(List.of(menu));
//            given(menuToMenuResponseConverter.convert(menu)).willReturn(menuResponse);
//
//            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menu/restaurant/{name}", restaurantName))
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(menuResponse.getId()));
//
//            verify(menuService, times(1)).getAllMenusByRestaurantName(restaurantName);
//        }
//    }
}

