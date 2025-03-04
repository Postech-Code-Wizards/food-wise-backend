package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.enums.UserType;
import br.com.foodwise.platform.gateway.database.jpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.foodwise.platform.factory.SecurityHelperFactory.buildMockUser;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class LoadUserEntityByUsernameUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoadUserByUsernameUseCase loadUserByUsernameUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_shouldReturnUserDetailsWhenEmailExists() {
        var email = "test@code-wizards.com";
        var mockUser = buildMockUser(email, "password", UserType.CUSTOMER);
        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        var userDetails = loadUserByUsernameUseCase.loadUserByUsername(email);

        assertNotNull(userDetails);
    }

}