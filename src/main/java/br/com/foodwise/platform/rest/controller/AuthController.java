package br.com.foodwise.platform.rest.controller;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.rest.dtos.request.AuthRequest;
import br.com.foodwise.platform.rest.dtos.response.AuthResponse;
import br.com.foodwise.platform.service.AuthService;
import br.com.foodwise.platform.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@Tag(name = "Authentication", description = "Authentication crud controller")
public class AuthController implements AuthApi {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthService authService;

    @Override
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        authService.validateUserIsActive(auth);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }

}
