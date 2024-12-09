package br.com.foodWise.foodWise.auth.service;

import br.com.foodWise.foodWise.auth.dtos.request.register.RegisterCustomerRequest;
import br.com.foodWise.foodWise.auth.dtos.request.register.RegisterRestaurantRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final CustomerProfileService customerProfileService;
    private final RestaurantProfileService restaurantProfileService;

    @Transactional
    public void registerCustomer(RegisterCustomerRequest request) {
        var userRequest = request.getUser();
        userService.createUser(userRequest.getEmail(), userRequest.getPassword(), userRequest.getRole());

        var customerRequest = request.getCustomer();
        customerProfileService.createCustomerProfile(customerRequest);
    }

    @Transactional
    public void registerRestaurant(RegisterRestaurantRequest request) {
        //User user = userService.createUser(request.getEmail(), request.getPassword(), request.getRole());

        // Create the restaurant profile
        //restaurantProfileService.createRestaurantProfile(user);
    }
}
