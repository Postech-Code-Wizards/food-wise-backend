package br.com.foodWise.infra.security.config;

import br.com.foodWise.infra.security.filter.SecurityFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

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