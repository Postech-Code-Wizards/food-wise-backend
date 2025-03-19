package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.application.facade.TokenFacade;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
class AuthControllerTest {

    private static UserEntity savedUser;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private TokenFacade tokenFacade;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp(@Autowired UserRepository userRepository) {
        savedUser = new UserEntity();
        savedUser.setEmail("test@code-wizards.com");
        savedUser.setPassword(new BCryptPasswordEncoder().encode("password"));
        savedUser.setUserType(UserType.CUSTOMER);

        userRepository.save(savedUser);
    }

    @Nested
    class LoginTests {

        @Test
        @DisplayName("Should return AuthResponse when authentication is successful")
        void shouldReturnAuthResponse_whenAuthenticationIsSuccessful() throws Exception {
            var email = "test@code-wizards.com";
            var password = "password";
            var token = "jwt-token";

            var authRequest = new AuthRequest(email, password);
            var authentication = mock(Authentication.class);

            when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                    .thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(savedUser);
            when(tokenFacade.generateToken(savedUser)).thenReturn(token);

            mockMvc.perform(post("/api/v1/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(authRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token").value(token));

            verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
            verify(tokenFacade, times(1)).generateToken(savedUser);
        }

        @Test
        void shouldReturnBadRequest_whenAuthenticationIsEmpty() throws Exception {
            var request = new AuthRequest("", "");

            mockMvc.perform(post("/api/v1/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }
}
