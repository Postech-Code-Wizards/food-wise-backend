package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.User;
import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.database.jpa.converter.UserDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.UserEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.UserEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.UserRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserJpaGatewayTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityToDomainConverter userEntityToDomainConverter;

    @Mock
    private UserDomainToEntityConverter userDomainToEntityConverter;

    @InjectMocks
    private UserJpaGateway userJpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find UserDetails by email")
    void should_find_UserDetails_by_email() {

        String email = Instancio.create(String.class);
        UserEntity userEntity = Mockito.mock(UserEntity.class);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);

        UserDetails result = userJpaGateway.findByEmail(email);

        assertEquals(userEntity, result);
        verify(userRepository).findByEmail(email);
    }

    @Test
    @DisplayName("Should check if User exists by email")
    void should_check_if_User_exists_by_email() {

        String email = Instancio.create(String.class);
        boolean exists = Instancio.create(Boolean.class);

        when(userRepository.existsByEmail(email)).thenReturn(exists);

        boolean result = userJpaGateway.existsByEmail(email);

        assertEquals(exists, result);
        verify(userRepository).existsByEmail(email);
    }

    @Test
    @DisplayName("Should find User by id and UserType when User exists")
    void should_find_User_by_id_and_UserType_when_User_exists() {

        long id = Instancio.create(Long.class);
        UserType userType = Instancio.create(UserType.class);
        UserEntity userEntity = Instancio.create(UserEntity.class);
        User user = Instancio.create(User.class);

        when(userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType)).thenReturn(Optional.of(userEntity));
        when(userEntityToDomainConverter.convert(userEntity)).thenReturn(user);

        Optional<User> result = userJpaGateway.findByIdAndUserTypeAndDeletedAtIsNull(id, userType);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository).findByIdAndUserTypeAndDeletedAtIsNull(id, userType);
        verify(userEntityToDomainConverter).convert(userEntity);
    }

    @Test
    @DisplayName("Should return empty Optional when User is not found by id and UserType")
    void should_return_empty_Optional_when_User_is_not_found_by_id_and_UserType() {
        long id = Instancio.create(Long.class);
        UserType userType = Instancio.create(UserType.class);

        when(userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType)).thenReturn(Optional.empty());

        Optional<User> result = userJpaGateway.findByIdAndUserTypeAndDeletedAtIsNull(id, userType);

        assertFalse(result.isPresent());
        verify(userRepository).findByIdAndUserTypeAndDeletedAtIsNull(id, userType);
    }

    @Test
    @DisplayName("Should save User")
    void should_save_User() {

        User user = Instancio.create(User.class);
        UserEntity userEntity = Instancio.create(UserEntity.class);
        UserEntity savedEntity = Instancio.create(UserEntity.class);
        User savedUser = Instancio.create(User.class);

        when(userDomainToEntityConverter.convert(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(savedEntity);
        when(userEntityToDomainConverter.convert(savedEntity)).thenReturn(savedUser);

        User result = userJpaGateway.save(user);

        assertEquals(savedUser, result);
        verify(userDomainToEntityConverter).convert(user);
        verify(userRepository).save(userEntity);
        verify(userEntityToDomainConverter).convert(savedEntity);
    }

    @Test
    @DisplayName("Should delete User by id and UserType when User exists")
    void should_delete_User_by_id_and_UserType_when_User_exists() {

        long id = Instancio.create(Long.class);
        UserType userType = Instancio.create(UserType.class);
        UserEntity userEntity = Instancio.create(UserEntity.class);

        when(userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType)).thenReturn(Optional.of(userEntity));

        userJpaGateway.delete(id, userType);

        verify(userRepository).findByIdAndUserTypeAndDeletedAtIsNull(id, userType);
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Should log User not found when delete User by id and UserType User not exists")
    void should_log_User_not_found_when_delete_User_by_id_and_UserType_User_not_exists() {

        long id = Instancio.create(Long.class);
        UserType userType = Instancio.create(UserType.class);

        when(userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType)).thenReturn(Optional.empty());

        userJpaGateway.delete(id, userType);

        verify(userRepository).findByIdAndUserTypeAndDeletedAtIsNull(id, userType);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Should log User not found when find User by id and UserType User not exists")
    void should_log_User_not_found_when_find_User_by_id_and_UserType_User_not_exists() {

        long id = Instancio.create(Long.class);
        UserType userType = Instancio.create(UserType.class);

        when(userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType)).thenReturn(Optional.empty());

        userJpaGateway.findByIdAndUserTypeAndDeletedAtIsNull(id, userType);

        verify(userRepository).findByIdAndUserTypeAndDeletedAtIsNull(id, userType);
    }

    @Test
    @DisplayName("Should find User by ID and convert to domain")
    void findUserById_ShouldFindAndConvert() {

        Long userId = Instancio.create(Long.class);
        UserEntity userEntity = Instancio.create(UserEntity.class);
        User expectedUser = Instancio.create(User.class);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userEntityToDomainConverter.convert(userEntity)).thenReturn(expectedUser);

        User actualUser = userJpaGateway.findUserById(userId);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when User is not found")
    void findUserById_ShouldThrowResourceNotFound() {

        Long userId = Instancio.create(Long.class);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userJpaGateway.findUserById(userId));
    }

}