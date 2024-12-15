package br.com.foodwise.platform.infra.security.config;

import br.com.foodwise.platform.infra.security.filter.SecurityFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    @Mock
    private SecurityFilter mockSecurityFilter;

    @InjectMocks
    private SecurityConfig securityConfig;

    @Test
    void shouldCreatePasswordEncoderSuccessfully() {
        assertNotNull(securityConfig.passwordEncoder());
    }

}