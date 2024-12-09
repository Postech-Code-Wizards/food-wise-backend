package br.com.foodWise.foodWise.auth.rest.controller;

import br.com.foodWise.foodWise.auth.dtos.request.AuthRequest;
import br.com.foodWise.foodWise.auth.dtos.request.register.RegisterCustomerRequest;
import br.com.foodWise.foodWise.auth.dtos.request.register.RegisterRestaurantRequest;
import br.com.foodWise.foodWise.auth.dtos.response.AuthResponse;
import br.com.foodWise.foodWise.auth.service.RegistrationService;
import br.com.foodWise.foodWise.auth.service.TokenService;
import br.com.foodWise.foodWise.model.entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final RegistrationService registrationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register/customer")
    public ResponseEntity<Void> registerCustomer(@RequestBody @Valid RegisterCustomerRequest request) {
        registrationService.registerCustomer(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/restaurant")
    public ResponseEntity<Void> registerRestaurant(@RequestBody @Valid RegisterRestaurantRequest request) {
        registrationService.registerRestaurant(request);
        return ResponseEntity.ok().build();
    }

}