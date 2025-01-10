package br.com.foodwise.platform.rest.controller;


import br.com.foodwise.platform.model.entities.enums.UserType;
import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.service.RestaurantProfileService;
import br.com.foodwise.platform.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static br.com.foodwise.platform.factory.RequestFactory.buildRestaurantProfileRequest;
import static br.com.foodwise.platform.factory.RequestFactory.buildValidRegisterRestaurantRequest;
import static br.com.foodwise.platform.factory.ResponseFactory.buildRestaurantProfileResponse;
import static br.com.foodwise.platform.factory.SecurityHelperFactory.authenticateUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class RestaurantProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantProfileService restaurantProfileService;

    @MockBean
    private UserService userService;

    @InjectMocks
    private RestaurantProfileController restaurantProfileController;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class AuthenticatedEndpointsContext {
        private static final String TEST_EMAIL = "test@code-wizards.com";

        @BeforeEach
        void setUp() {
            authenticateUser(TEST_EMAIL, "testPassword", UserType.CUSTOMER);
        }

        @Test
        void shouldRetrieveMyProfileSuccessfully() throws Exception {
            var response = buildRestaurantProfileResponse();

            when(restaurantProfileService.retrieveRestaurantByEmail(TEST_EMAIL)).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant/my-profile"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.businessName").value(response.getBusinessName()));

            verify(restaurantProfileService).retrieveRestaurantByEmail(TEST_EMAIL);
        }

        @Test
        void shouldRetrieveRestaurantByBusinessNameSuccessfully() throws Exception {
            var response = buildRestaurantProfileResponse();
            var businessName = response.getBusinessName();

            when(restaurantProfileService.retrieveRestaurantByBusinessName(businessName)).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant")
                            .param("businessName", businessName))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.businessName").value(businessName));

            verify(restaurantProfileService).retrieveRestaurantByBusinessName(businessName);
        }
    }

    @Nested
    class RegisterContext {
        @ParameterizedTest
        @CsvSource({
                "user, null, 'User information is required'",
                "restaurant, null, 'Restaurant profile information is required'",
                "businessName, '', 'Business name is required'",
                "cuisineType, '', 'Cuisine type is required'",
                "address, null, 'Address is required'",
                "businessHours, '25:00-26:00', 'Business hours must match the pattern HH:mm-HH:mm'",
                "businessHours, '09:00-25:00', 'Business hours must match the pattern HH:mm-HH:mm'"
        })
        void shouldRejectInvalidFields(String fieldName, String fieldValue, String expectedMessage) throws Exception {
            var request = buildValidRegisterRestaurantRequest();
            setInvalidField(request, fieldName, fieldValue);

            var requestBody = objectMapper.writeValueAsString(request);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/restaurant/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message")
                            .value(expectedMessage));
        }

        private void setInvalidField(RegisterRestaurantRequest request, String fieldName, String fieldValue) {
            switch (fieldName) {
                case "user" -> request.setUser(null);
                case "restaurant" -> request.setRestaurant(null);
                case "businessName" -> request.getRestaurant().setBusinessName(fieldValue);
                case "businessHours" -> request.getRestaurant().setBusinessHours(fieldValue);
                case "cuisineType" -> request.getRestaurant().setCuisineType(fieldValue);
                case "address" -> request.getRestaurant().setAddress(null);
                default -> throw new IllegalArgumentException("Unknown field: " + fieldName);
            }
        }

        @Test
        void shouldRegisterRestaurantSuccessfully() throws Exception {
            var request = buildValidRegisterRestaurantRequest();
            var requestBody = objectMapper.writeValueAsString(request);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/restaurant/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isCreated());

            verify(restaurantProfileService, times(1)).registerRestaurant(any(RegisterRestaurantRequest.class));
        }
    }

    @Nested
    class updateRestaurantProfile {
        private static final String TEST_EMAIL = "test@code-wizards.com";

        @BeforeEach
        void setUp() {
            authenticateUser(TEST_EMAIL, "testPassword", UserType.CUSTOMER);
        }

        @Test
        @DisplayName("Should return 204 when restaurant profile is updated successfully")
        void changeMyRestaurantProfileSuccess() throws Exception {
            Long id = 1L;
            var restaurantRequest = buildRestaurantProfileRequest();
            String requestBody = objectMapper.writeValueAsString(restaurantRequest);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/restaurant/{id}/profile", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

            verify(restaurantProfileService, times(1)).updateRestaurantProfile(any(RestaurantProfileRequest.class), eq(id));
        }

        @Test
        @DisplayName("Should return 400 when restaurant profile is empty")
        void changeMyRestaurantProfileEmpty() throws Exception {
            Long id = 1L;
            String requestBody = objectMapper.writeValueAsString(new RestaurantProfileRequest());

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/restaurant/{id}/profile", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());

            verify(restaurantProfileService, times(0)).updateRestaurantProfile(any(RestaurantProfileRequest.class), eq(id));
        }

        @Test
        @DisplayName("Should return 204 when restaurant USER is updated successfully")
        void changeMyRestaurantUserSuccess() throws Exception {
            Long id = 1L;
            var userRequest = new UserRequest(TEST_EMAIL, "newPassword");
            String requestBody = objectMapper.writeValueAsString(userRequest);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/restaurant/{id}/credentials", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

            verify(userService, times(1)).updateUser(any(UserRequest.class), eq(id));
        }

        @Test
        @DisplayName("Should return 400 when restaurant USER is empty")
        void changeMyRestaurantUserEmpty() throws Exception {
            Long id = 1L;
            String requestBody = objectMapper.writeValueAsString(new UserRequest());

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/restaurant/{id}/credentials", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());

            verify(userService, times(0)).updateUser(any(UserRequest.class), eq(id));
        }
    }

}
