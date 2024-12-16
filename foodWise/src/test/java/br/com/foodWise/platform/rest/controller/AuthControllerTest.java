package br.com.foodwise.platform.rest.controller;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.rest.controller.AuthController;
import br.com.foodwise.platform.rest.dtos.request.AuthRequest;
import br.com.foodwise.platform.rest.dtos.response.AuthResponse;
import br.com.foodwise.platform.service.TokenService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthController authController;

    @Test
    void shouldReturnAuthResponse_whenAuthenticationIsSuccessful() {
        var email = "test@code-wizards.com";
        var password = "password";
        var token = "jwt-token";

        var authRequest = new AuthRequest(email, password);
        var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        var user = mock(User.class);
        var authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(tokenService.generateToken(user)).thenReturn(token);

        ResponseEntity<AuthResponse> response = authController.login(authRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(token, response.getBody().token());

        verify(authenticationManager).authenticate(authenticationToken);
        verify(tokenService).generateToken(user);
    }


    @Test
    void shouldThrowException_whenAuthenticationFails() {
        var email = "test@code-wizards.com";
        var password = "password";
        var authRequest = new AuthRequest(email, password);
        var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        when(authenticationManager.authenticate(authenticationToken))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        assertThrows(BadCredentialsException.class, () -> authController.login(authRequest));

        verify(authenticationManager).authenticate(authenticationToken);
        verifyNoInteractions(tokenService);
    }
}
