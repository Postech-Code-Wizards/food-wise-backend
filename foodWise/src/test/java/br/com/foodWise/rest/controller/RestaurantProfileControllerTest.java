package br.com.foodwise.rest.controller;


import br.com.foodwise.rest.dtos.request.register.restaurant.RegisterRestaurantRequest;
import br.com.foodwise.service.RestaurantProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static br.com.foodwise.factory.RequestFactory.buildValidRegisterRestaurantRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class RestaurantProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantProfileService restaurantProfileService;

    @InjectMocks
    private RestaurantProfileController restaurantProfileController;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @CsvSource({
            "user, null, 'User information is required'",
            "restaurant, null, 'Restaurant profile information is required'",
            "businessName, '', 'Business name is required'",
            "businessHours, '', 'Business hours are required'",
            "cuisineType, '', 'Cuisine type is required'",
            "address, null, 'Address is required'",
            "businessHours, '25:00-26:00', 'Business hours must match the pattern HH:mm-HH:mm'",
            "businessHours, '09:00-25:00', 'Business hours must match the pattern HH:mm-HH:mm'"})
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

}