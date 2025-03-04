package br.com.foodwise.platform.rest.controller;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class RestaurantProfileEntityControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private RestaurantProfileService restaurantProfileService;
//
//    @MockBean
//    private UserService userService;
//
//    @InjectMocks
//    private RestaurantProfileController restaurantProfileController;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Nested
//    class AuthenticatedEndpointsContext {
//        private static final String TEST_EMAIL = "test@code-wizards.com";
//
//        @BeforeEach
//        void setUp() {
//            authenticateUser(TEST_EMAIL, "testPassword", UserType.CUSTOMER);
//        }
//
//        @Test
//        void shouldRetrieveMyProfileSuccessfully() throws Exception {
//            var response = buildRestaurantProfileResponse();
//
//            when(restaurantProfileService.retrieveRestaurantByEmail(TEST_EMAIL)).thenReturn(response);
//
//            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant/my-profile"))
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.jsonPath("$.businessName").value(response.getBusinessName()));
//
//            verify(restaurantProfileService).retrieveRestaurantByEmail(TEST_EMAIL);
//        }
//
//        @Test
//        void shouldRetrieveRestaurantByBusinessNameSuccessfully() throws Exception {
//            var response = buildRestaurantProfileResponse();
//            var businessName = response.getBusinessName();
//
//            when(restaurantProfileService.retrieveRestaurantByBusinessName(businessName)).thenReturn(response);
//
//            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant")
//                            .param("businessName", businessName))
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.jsonPath("$.businessName").value(businessName));
//
//            verify(restaurantProfileService).retrieveRestaurantByBusinessName(businessName);
//        }
//
//        @Test
//        void deleteRestaurant() throws Exception {
//            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/restaurant/1"))
//                    .andExpect(MockMvcResultMatchers.status().isOk());
//        }
//
//        @Test
//        void deleteInvalidRestaurant() throws Exception {
//            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/restaurant/a"))
//                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
//        }
//
//        @Test
//        void shouldRetrieveRestaurantByEmailSuccessfully() throws Exception {
//            var response = buildRestaurantProfileResponse();
//
//            given(restaurantProfileService.retrieveRestaurantByEmail(TEST_EMAIL)).willReturn(response);
//
//            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant/retrieve-login")
//                            .param("email", TEST_EMAIL))
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.jsonPath("$.businessName").value(response.getBusinessName()));
//
//            verify(restaurantProfileService).retrieveRestaurantByEmail(TEST_EMAIL);
//        }
//
//    }
//
//    @Nested
//    class RegisterContext {
//        @ParameterizedTest
//        @CsvSource({
//                "user, null, 'User information is required'",
//                "restaurant, null, 'Restaurant profile information is required'",
//                "businessName, '', 'Business name is required'",
//                "cuisineType, '', 'Cuisine type is required'",
//                "address, null, 'Address is required'",
//                "businessHours, '25:00-26:00', 'Business hours must match the pattern HH:mm-HH:mm'",
//                "businessHours, '09:00-25:00', 'Business hours must match the pattern HH:mm-HH:mm'"
//        })
//        void shouldRejectInvalidFields(String fieldName, String fieldValue, String expectedMessage) throws Exception {
//            var request = buildValidRegisterRestaurantRequest();
//            setInvalidField(request, fieldName, fieldValue);
//
//            var requestBody = objectMapper.writeValueAsString(request);
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/restaurant/register")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(requestBody))
//                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                    .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message")
//                            .value(expectedMessage));
//        }
//
//        private void setInvalidField(RegisterRestaurantRequest request, String fieldName, String fieldValue) {
//            switch (fieldName) {
//                case "user" -> request.setUser(null);
//                case "restaurant" -> request.setRestaurant(null);
//                case "businessName" -> request.getRestaurant().setBusinessName(fieldValue);
//                case "businessHours" -> request.getRestaurant().setBusinessHours(fieldValue);
//                case "cuisineType" -> request.getRestaurant().setCuisineType(fieldValue);
//                case "address" -> request.getRestaurant().setAddress(null);
//                default -> throw new IllegalArgumentException("Unknown field: " + fieldName);
//            }
//        }
//
//        @Test
//        void shouldRegisterRestaurantSuccessfully() throws Exception {
//            var request = buildValidRegisterRestaurantRequest();
//            var requestBody = objectMapper.writeValueAsString(request);
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/restaurant/register")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(requestBody))
//                    .andExpect(MockMvcResultMatchers.status().isCreated());
//
//            verify(restaurantProfileService, times(1)).registerRestaurant(any(RegisterRestaurantRequest.class));
//        }
//    }
//
//    @Nested
//    class NotAuthenticatedEndpointsContext {
//
//        @Test
//        void deleteRestaurantNotAuthenticated() throws Exception {
//            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/restaurant/1"))
//                    .andExpect(MockMvcResultMatchers.status().isForbidden());
//        }
//
//    }
//
//    @Nested
//    class updateRestaurantProfileEntity {
//        private static final String TEST_EMAIL = "test@code-wizards.com";
//
//        @BeforeEach
//        void setUp() {
//            reset(restaurantProfileService, userService);
//            authenticateUser(TEST_EMAIL, "testPassword", UserType.CUSTOMER);
//        }
//
//
//    }

}
