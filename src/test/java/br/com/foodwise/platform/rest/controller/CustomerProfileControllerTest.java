package br.com.foodwise.platform.rest.controller;

import br.com.foodwise.platform.model.entities.enums.UserType;
import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodwise.platform.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.service.CustomerProfileService;
import br.com.foodwise.platform.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static br.com.foodwise.platform.factory.RequestFactory.buildCustomerProfileRequest;
import static br.com.foodwise.platform.factory.RequestFactory.buildRegisterCustomerRequest;
import static br.com.foodwise.platform.factory.ResponseFactory.buildCustomerProfileResponse;
import static br.com.foodwise.platform.factory.SecurityHelperFactory.authenticateUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class CustomerProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerProfileService customerProfileService;

    @MockBean
    private UserService userService;

    @InjectMocks
    private CustomerProfileController customerProfileController;

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
        void shouldRetrieveCustomerByEmailSuccessfully() throws Exception {
            var response = buildCustomerProfileResponse();

            given(customerProfileService.retrieveCustomerByEmail(TEST_EMAIL)).willReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/retrieve-login")
                            .param("email", TEST_EMAIL))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(response.getFirstName()));

            verify(customerProfileService).retrieveCustomerByEmail(TEST_EMAIL);
        }

        @Test
        void shouldRetrieveMyProfileSuccessfully() throws Exception {
            var response = buildCustomerProfileResponse();
            when(customerProfileService.retrieveCustomerByEmail(any())).thenReturn(response);

            given(customerProfileService.retrieveCustomerByEmail(TEST_EMAIL)).willReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/my-profile"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(response.getFirstName()));

            verify(customerProfileService).retrieveCustomerByEmail("test@code-wizards.com");
        }

        @Test
        void deleteCustomer() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customer/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void deleteInvalidCustomer() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customer/a"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

    }

    @Nested
    class RegisterContext {

        @Test
        void shouldRegisterCustomerSuccessfully() throws Exception {
            var request = buildRegisterCustomerRequest("test@code-wizards.com", "password123");
            var requestBody = objectMapper.writeValueAsString(request);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isCreated());

            verify(customerProfileService, times(1))
                    .registerCustomer(any(RegisterCustomerRequest.class));
        }

        @Test
        void shouldRejectInvalidPhoneNumber() throws Exception {
            var invalidPhoneNumber = "1234abcd5678";
            var request = buildRegisterCustomerRequest("test@code-wizards.com", "password123");
            request.getCustomer().getPhone().setPhoneNumber(invalidPhoneNumber);

            var requestBody = objectMapper.writeValueAsString(request);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message").value("Phone number must only contain digits"));

            verify(customerProfileService, times(0)).registerCustomer(any(RegisterCustomerRequest.class));
        }

    }

    @Nested
    class NotAuthenticatedEndpointsContext {

        @Test
        void deleteCustomerNotAuthenticated() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customer/1"))
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }

    }

    @Nested
    class UpdateContext {

        private static final String TEST_EMAIL = "test@code-wizards.com";

        @BeforeEach
        void setUp() {
            authenticateUser(TEST_EMAIL, "testPassword", UserType.CUSTOMER);
        }

        @Test
        @DisplayName("Should return 204 when customer profile is updated successfully")
        void changeMyCustomerProfileSuccess() throws Exception {
            Long id = 1L;
            var customerProfileRequest = buildCustomerProfileRequest();
            String requestBody = objectMapper.writeValueAsString(customerProfileRequest);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customer/{id}/profile", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

            verify(customerProfileService, times(1)).updateCustomerProfile(any(CustomerProfileRequest.class), eq(id));
        }

        @Test
        @DisplayName("Should return 400 when customer profile is empty")
        void changeMyCustomerProfileEmpty() throws Exception {
            Long id = 1L;
            var invalidRequest = new CustomerProfileRequest();
            String requestBody = objectMapper.writeValueAsString(invalidRequest);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customer/{id}/profile", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());

            verify(customerProfileService, times(0)).updateCustomerProfile(any(CustomerProfileRequest.class), eq(id));
        }


        @Test
        @DisplayName("Should return 204 when user email is updated successfully")
        void changeMyCustomerUserCredentialsSuccess() throws Exception {
            Long id = 1L;
            var customerCredentialsUpdate = new UserRequest(TEST_EMAIL, "newPassword");
            var request = objectMapper.writeValueAsString(customerCredentialsUpdate);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customer/{id}/updateEmail", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

            verify(userService, times(1)).updateUserEmail(any(UserRequest.class), eq(id));
        }

        @Test
        @DisplayName("Should return 400 when user email is empty")
        void changeMyCustomerUserCredentialsEmpty() throws Exception {
            Long id = 1L;
            var customerCredentialsUpdate = new UserRequest("test@code-wizards.com", "");
            var request = objectMapper.writeValueAsString(customerCredentialsUpdate);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customer/{id}/updateEmail", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());

            verify(userService, times(0)).updateUserEmail(any(UserRequest.class), eq(id));
        }
    }
}
