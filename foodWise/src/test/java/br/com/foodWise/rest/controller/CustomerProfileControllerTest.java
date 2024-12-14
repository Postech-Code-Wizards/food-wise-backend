package br.com.foodwise.rest.controller;

import br.com.foodwise.model.entities.enums.UserType;
import br.com.foodwise.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodwise.service.CustomerProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static br.com.foodwise.factory.RequestFactory.buildRegisterCustomerRequest;
import static br.com.foodwise.factory.ResponseFactory.buildCustomerProfileResponse;
import static br.com.foodwise.factory.SecurityHelperFactory.authenticateUser;
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

    @Test
    void shouldRegisterCustomerSuccessfully() throws Exception {
        var request = buildRegisterCustomerRequest("test@example.com", "password123");
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
        var request = buildRegisterCustomerRequest("test@example.com", "password123");
        request.getCustomer().getPhone().setPhoneNumber(invalidPhoneNumber);

        var requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message").value("Phone number must only contain digits"));

        verify(customerProfileService, times(0)).registerCustomer(any(RegisterCustomerRequest.class));
    }

    @Test
    void shouldRetrieveCustomerByEmailSuccessfully() throws Exception {
        var email = "test@example.com";
        authenticateUser(email, "testPassword", UserType.CUSTOMER);

        var response = buildCustomerProfileResponse();

        given(customerProfileService.retrieveCustomerByEmail(email)).willReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer")
                        .param("email", email))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(response.getFirstName()));

        verify(customerProfileService).retrieveCustomerByEmail(email);
    }

    @Test
    void shouldRetrieveMyProfileSuccessfully() throws Exception {
        var response = buildCustomerProfileResponse();
        when(customerProfileService.retrieveCustomerByEmail(any())).thenReturn(response);

        var email = "test@example.com";
        authenticateUser(email, "testPassword", UserType.CUSTOMER);

        given(customerProfileService.retrieveCustomerByEmail(email)).willReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/my-profile"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(response.getFirstName()));

        verify(customerProfileService).retrieveCustomerByEmail("test@example.com");
    }
}