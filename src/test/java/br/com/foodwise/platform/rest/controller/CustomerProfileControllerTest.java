package br.com.foodwise.platform.rest.controller;

import br.com.foodwise.platform.model.entities.enums.UserType;
import br.com.foodwise.platform.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.platform.service.CustomerProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

import static br.com.foodwise.platform.factory.RequestFactory.buildRegisterCustomerRequest;
import static br.com.foodwise.platform.factory.ResponseFactory.buildCustomerProfileResponse;
import static br.com.foodwise.platform.factory.SecurityHelperFactory.authenticateUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class CustomerProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerProfileService customerProfileService;

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
}
