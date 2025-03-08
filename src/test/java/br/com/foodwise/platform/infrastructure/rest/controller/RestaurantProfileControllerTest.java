package br.com.foodwise.platform.infrastructure.rest.controller;


import br.com.foodwise.platform.application.facade.RestaurantProfileFacade;
import br.com.foodwise.platform.application.facade.UserFacade;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.RestaurantProfileResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
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

import static br.com.foodwise.platform.factory.RequestFactory.buildValidRegisterRestaurantRequest;
import static br.com.foodwise.platform.factory.SecurityHelperFactory.authenticateUser;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class RestaurantProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantProfileFacade restaurantProfileFacade;

    @MockBean
    private UserFacade userFacade;

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
            var response = Instancio.create(RestaurantProfileResponse.class);

            when(restaurantProfileFacade.retrieveRestaurantByEmail()).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant/my-profile"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.businessName").value(response.getBusinessName()));

            verify(restaurantProfileFacade).retrieveRestaurantByEmail();
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

            given(restaurantProfileFacade.retrieveRestaurantByEmail()).willReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant/retrieve-login")
                            .param("email", TEST_EMAIL))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.businessName").value(response.getBusinessName()));

            verify(restaurantProfileFacade).retrieveRestaurantByEmail();
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
            authenticateUser(TEST_EMAIL, "testPassword", UserType.CUSTOMER);
        }


    }

}
