package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.facade.AuthFacade;
import br.com.foodwise.platform.application.facade.TokenFacade;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.AuthRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.AuthResponse;
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
    private final TokenFacade tokenFacade;

    @Override
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        AuthFacade.validateUserIsActive(auth);
        var token = tokenFacade.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }

}
