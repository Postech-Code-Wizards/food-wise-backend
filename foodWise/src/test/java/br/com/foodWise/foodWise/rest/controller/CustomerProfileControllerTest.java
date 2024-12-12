package br.com.foodWise.foodWise.rest.controller;

import br.com.foodWise.foodWise.model.entities.enums.PhoneType;
import br.com.foodWise.foodWise.model.entities.enums.UserType;
import br.com.foodWise.foodWise.rest.dtos.request.register.AddressRequest;
import br.com.foodWise.foodWise.rest.dtos.request.register.PhoneRequest;
import br.com.foodWise.foodWise.rest.dtos.request.register.UserRequest;
import br.com.foodWise.foodWise.rest.dtos.request.register.customer.CustomerProfileRequest;
import br.com.foodWise.foodWise.rest.dtos.request.register.customer.RegisterCustomerRequest;
import br.com.foodWise.foodWise.rest.dtos.response.CustomerProfileResponse;
import br.com.foodWise.foodWise.service.CustomerProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(CustomerProfileController.class)
class CustomerProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerProfileService customerProfileService;

    @InjectMocks
    private CustomerProfileController customerProfileController;

    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterCustomerSuccessfully() throws Exception {
        var request = createRegisterCustomerRequest("test@example.com", "password123");
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
        var request = createRegisterCustomerRequest("test@example.com", "password123");
        request.getCustomer().getPhone().setPhoneNumber(invalidPhoneNumber);

        var requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())  // Expect a Bad Request because of the invalid phone number
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message").value("Phone number must only contain digits"));

        verify(customerProfileService, times(0)).registerCustomer(any(RegisterCustomerRequest.class));
    }

    @Test
    void shouldRetrieveCustomerByEmailSuccessfully() throws Exception {
        var email = "test@example.com";
        var response = new CustomerProfileResponse();

        given(customerProfileService.retrieveCustomerByEmail(email)).willReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer")
                        .param("email", email))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(email));

        verify(customerProfileService).retrieveCustomerByEmail(email);
    }

    @Test
    @WithMockUser(username = "test@example.com")
    void shouldRetrieveMyProfileSuccessfully() throws Exception {
        CustomerProfileResponse response = new CustomerProfileResponse();

        given(customerProfileService.retrieveCustomerByEmail("test@example.com")).willReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/my-profile"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@example.com"));

        verify(customerProfileService).retrieveCustomerByEmail("test@example.com");
    }


    private RegisterCustomerRequest createRegisterCustomerRequest(String email, String password) {
        return new RegisterCustomerRequest(
                new UserRequest(email, password, UserType.CUSTOMER),
                new CustomerProfileRequest(
                        "John", "Doe",
                        new AddressRequest("123 Main St", "City", "State", "Neighborhood", "12345", "Country", null, null),
                        new PhoneRequest("555", "1234567890", PhoneType.MOBILE)
                )
        );
    }


}