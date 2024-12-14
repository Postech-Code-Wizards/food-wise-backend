package br.com.foodwise.infra.security.config;

import br.com.foodwise.infra.security.filter.SecurityFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    @Mock
    private SecurityFilter mockSecurityFilter;

    @InjectMocks
    private SecurityConfig securityConfig;

    @Test
    public void shouldCreateSecurityFilterChainSuccessfully() throws Exception {

    }

    @Test
    public void shouldCreatePasswordEncoderSuccessfully() {
        assertNotNull(securityConfig.passwordEncoder());
    }

}