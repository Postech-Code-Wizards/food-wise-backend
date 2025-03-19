package br.com.foodwise.platform.infrastructure.rest.controller;


import br.com.foodwise.platform.application.facade.RestaurantProfileFacade;
import br.com.foodwise.platform.application.facade.UserFacade;
import br.com.foodwise.platform.domain.RestaurantOwner;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantOwnerRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.IsDeliveryRestaurantResponse;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
import static br.com.foodwise.platform.factory.RequestFactory.buildrestaurantOwnerRequest;
import static br.com.foodwise.platform.factory.SecurityHelperFactory.authenticateUser;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class RestaurantProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantProfileFacade restaurantProfileFacade;

    @MockBean
    private UserFacade userFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class AuthenticatedEndpointsContext {
        private static final String TEST_EMAIL = "test@code-wizards.com";

        @BeforeEach
        void setUp() {
            authenticateUser(TEST_EMAIL, "testPassword", UserType.RESTAURANT_OWNER);
        }

        @Test
        void shouldRetrieveMyProfileSuccessfully() throws Exception {
            var response = Instancio.create(RestaurantProfileResponse.class);
            var responseOwner = Instancio.create(RestaurantOwner.class);
            response.setRestaurantOwner(responseOwner);

            when(restaurantProfileFacade.retrieveMyProfile()).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant/my-profile"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.businessName").value(response.getBusinessName()));

            verify(restaurantProfileFacade).retrieveMyProfile();
        }

        @Test
        void shouldRetrieveRestaurantByBusinessNameSuccessfully() throws Exception {
            var response = Instancio.create(RestaurantProfileResponse.class);
            var businessName = response.getBusinessName();

            when(restaurantProfileFacade.retrieveRestaurantByBusinessName(businessName)).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant")
                            .param("businessName", businessName))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.businessName").value(businessName));

            verify(restaurantProfileFacade).retrieveRestaurantByBusinessName(businessName);
        }

        @Test
        void deleteRestaurant() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/restaurant/1"))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());
        }

        @Test
        void deleteInvalidRestaurant() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/restaurant/a"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        void shouldRetrieveRestaurantByEmailSuccessfully() throws Exception {
            var response = Instancio.create(RestaurantProfileResponse.class);

            given(restaurantProfileFacade.retrieveRestaurantByEmail(TEST_EMAIL)).willReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant/retrieve-login")
                            .param("email", TEST_EMAIL))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.businessName").value(response.getBusinessName()));

            verify(restaurantProfileFacade).retrieveRestaurantByEmail(TEST_EMAIL);
        }

        @Test
        void updatePasswordRestaurantUserCredentialsSuccess() throws Exception {

            var passwordRequest = new PasswordRequest("12345678", "87654321");
            var request = objectMapper.writeValueAsString(passwordRequest);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/restaurant/updatePassword")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

            verify(userFacade, times(1)).updatePassword(any(PasswordRequest.class));
        }

        @Test
        void updateEmailRestaurantUserSuccess() throws Exception {

            var userRequest = new UserRequest(TEST_EMAIL, "newPassword");
            var request = objectMapper.writeValueAsString(userRequest);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/restaurant/{id}/updateEmail", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

            verify(restaurantProfileFacade, times(1)).updateRestaurantUserEmail(any(UserRequest.class), anyLong());
        }

        @Test
        void updateRestaurantUserSuccess() throws Exception {

            var restaurantProfileRequest = buildRestaurantProfileRequest();
            var request = objectMapper.writeValueAsString(restaurantProfileRequest);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/restaurant/{id}/profile", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

            verify(restaurantProfileFacade, times(1)).updateRestaurantProfile(any(RestaurantProfileRequest.class), anyLong());
        }

        @Test
        void updateRestaurantOwner_ShouldUpdateSuccessfully() throws Exception {

            var registerRestaurantOwnerRequest = buildrestaurantOwnerRequest();
            Long userId = 1L;
            var request = objectMapper.writeValueAsString(registerRestaurantOwnerRequest);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/restaurant/profile-owner/{userId}", userId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

            verify(restaurantProfileFacade, times(1)).updateRestaurantOwner(any(RegisterRestaurantOwnerRequest.class), eq(userId));
        }

        @Test
        void retrieveRestaurantById_ShouldReturnOkAndResponse() throws Exception {

            IsDeliveryRestaurantResponse expectedResponse = Instancio.create(IsDeliveryRestaurantResponse.class);

            when(restaurantProfileFacade.retrieveRestaurantById(anyLong())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant/{id}/availability", expectedResponse.getId()))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedResponse.getId()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.businessName").value(expectedResponse.getBusinessName()));

            verify(restaurantProfileFacade, times(1)).retrieveRestaurantById(anyLong());
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
                case "owner" -> request.setOwner(null);
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

            verify(restaurantProfileFacade, times(1)).registerRestaurant(any(RegisterRestaurantRequest.class));
        }
    }

    @Nested
    class NotAuthenticatedEndpointsContext {

        @Test
        void deleteRestaurantNotAuthenticated() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/restaurant/1"))
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }

    }

    @Nested
    class updateRestaurantProfile {
        private static final String TEST_EMAIL = "test@code-wizards.com";

        @BeforeEach
        void setUp() {
            reset(restaurantProfileFacade, userFacade);
            authenticateUser(TEST_EMAIL, "testPassword", UserType.RESTAURANT_OWNER);
        }
    }

}
