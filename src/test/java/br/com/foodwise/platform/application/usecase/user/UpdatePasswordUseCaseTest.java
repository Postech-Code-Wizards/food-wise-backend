package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.gateway.UserGateway;
import br.com.foodwise.platform.gateway.database.jpa.converter.UserEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.instancio.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdatePasswordUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @Mock
    private Authentication authentication;

    @Mock
    private UserEntityToDomainConverter userEntityToDomainConverter;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private UpdatePasswordUseCase updatePasswordUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @DisplayName("Should throw BusinessException when password is incorrect")
    void testExecuteShouldThrowBusinessExceptionWhenPasswordIsIncorrect() {

        String newPassword = Instancio.create(String.class);
        User user = Instancio.create(User.class);
        UserEntity userEntity = Instancio.create(UserEntity.class);

        when(authentication.getPrincipal()).thenReturn(userEntity);
        BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);
        when(encoder.matches(user.getPassword(), user.getPassword())).thenReturn(false);
        when(userEntityToDomainConverter.convert(userEntity)).thenReturn(user);

        assertThrows(BusinessException.class, () -> updatePasswordUseCase.execute(user, newPassword));

    }

    @Test
    @DisplayName("Must update password and update date when password is correct")
    void testExecuteShouldUpdatePasswordAndUpdateDateWhenPasswordIsCorrect() {

        String newPassword = Instancio.create(String.class);
        User user = Instancio.create(User.class);

        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        Model<UserEntity> userEntityModel = Instancio.of(UserEntity.class)
                .set(field(UserEntity::getPassword), encodedPassword)
                .toModel();
        UserEntity userEntity = Instancio.create(userEntityModel);

        when(authentication.getPrincipal()).thenReturn(userEntity);
        when(userEntityToDomainConverter.convert(userEntity)).thenReturn(user);

        updatePasswordUseCase.execute(user, newPassword);

        verify(userEntityToDomainConverter, times(1)).convert(any());
        verify(userGateway, times(1)).save(any());

    }

}