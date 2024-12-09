package br.com.foodWise.foodWise.auth.controller;

import br.com.foodWise.foodWise.auth.dtos.AuthRequest;
import br.com.foodWise.foodWise.auth.dtos.AuthResponse;
import br.com.foodWise.foodWise.auth.dtos.RegisterCustomerRequest;
import br.com.foodWise.foodWise.auth.service.TokenService;
import br.com.foodWise.foodWise.model.entities.User;
import br.com.foodWise.foodWise.model.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register/customer")
    public ResponseEntity<Void> registerCustomer(@RequestBody @Valid RegisterCustomerRequest request){
        if(this.userRepository.findByEmail(request.getEmail()) != null)
            return ResponseEntity.badRequest().build();

        var encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());

        var newUser = User
                .builder()
                .email(request.getEmail())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isActive(true)
                .userType(request.getRole())
                .password(encryptedPassword)
                .build();

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/restaurant")
    public ResponseEntity<Void> registerRestaurant(@RequestBody @Valid RegisterCustomerRequest request){
        if(this.userRepository.findByEmail(request.getEmail()) != null)
            return ResponseEntity.badRequest().build();

        var encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());

        var newUser = User
                .builder()
                .email(request.getEmail())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isActive(true)
                .userType(request.getRole())
                .password(encryptedPassword)
                .build();

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
