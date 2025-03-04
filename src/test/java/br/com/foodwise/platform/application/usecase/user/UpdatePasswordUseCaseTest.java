package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdatePasswordUseCaseTest {

//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private Authentication authentication;
//
//    @Mock
//    private SecurityContext securityContext;
//
//    @InjectMocks
//    private UpdatePasswordUseCase updatePasswordUseCase;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//    }
//
//    @Test
//    void testExecuteShouldThrowBusinessExceptionWhenPasswordIsIncorrect() {
//
//        PasswordRequest passwordRequest = Instancio.create(PasswordRequest.class);
//        UserEntity userEntity = Instancio.create(UserEntity.class);
//
//        when(authentication.getPrincipal()).thenReturn(userEntity);
//        BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);
//        when(encoder.matches(passwordRequest.getPassword(), userEntity.getPassword())).thenReturn(false);
//
//        assertThrows(BusinessException.class, () -> updatePasswordUseCase.execute(passwordRequest));
//
//    }
//
//    @Test
//    void testExecuteShouldUpdatePasswordAndUpdateDateWhenPasswordIsCorrect() {
//
//        PasswordRequest passwordRequest = Instancio.create(PasswordRequest.class);
//        UserEntity userEntity = Instancio.create(UserEntity.class);
//
//
//        when(authentication.getPrincipal()).thenReturn(userEntity);
//        userEntity.setPassword(new BCryptPasswordEncoder().encode(passwordRequest.getPassword()));
//
//        updatePasswordUseCase.execute(passwordRequest);
//
//        verify(userRepository).save(userEntity);
//
//    }

}