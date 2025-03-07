package br.com.foodwise.platform.application.service;

import br.com.foodwise.platform.application.usecase.customer.*;
import br.com.foodwise.platform.application.usecase.user.DeleteUserUseCase;
import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.domain.User;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerProfileServiceTest {

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @Mock
    private UpdateCustomerUserEmailUseCase updateCustomerUserEmailUseCase;

    @Mock
    private DeleteCustomerProfileUseCase deleteCustomerProfileUseCase;

    @Mock
    private RetrieveCustomerByEmailUseCase retrieveCustomerByEmailUseCase;

    @Mock
    private RegisterCustomerUseCase registerCustomerUseCase;

    @Mock
    private UpdateCustomerProfileUseCase updateCustomerProfileUseCase;

    @InjectMocks
    private CustomerProfileService customerProfileService;

    private CustomerProfile customerProfile;
    private User user;

    @BeforeEach
    void setUp() {
        customerProfile = Instancio.create(CustomerProfile.class);
        user = Instancio.create(User.class);
    }

    @Test
    @DisplayName("Must call service to register a customer successfully")
    void registerCustomer_ShouldCallRegisterCustomerUseCase() {
        customerProfileService.registerCustomer(customerProfile);

        verify(registerCustomerUseCase, times(1)).execute(customerProfile);
    }

    @Test
    @DisplayName("Must call service to update a customer successfully")
    void updateCustomerProfile_ShouldCallUpdateCustomerProfileUseCase() {
        customerProfileService.updateCustomerProfile(customerProfile, 1L);

        verify(updateCustomerProfileUseCase, times(1)).execute(customerProfile, 1L);
    }

    @Test
    @DisplayName("Must call service to update email a customer successfully")
    void updateCustomerUserEmail_ShouldCallUpdateCustomerUserEmailUseCase() {
        customerProfileService.updateCustomerUserEmail(user, 1L);

        verify(updateCustomerUserEmailUseCase, times(1)).execute(user, 1L);
    }

    @Test
    @DisplayName("Must call service to retrieve by email a customer successfully")
    void retrieveCustomerByEmail_ShouldCallRetrieveCustomerByEmailUseCase() {
        when(retrieveCustomerByEmailUseCase.execute()).thenReturn(customerProfile);

        var result = customerProfileService.retrieveCustomerByEmail();

        assertEquals(customerProfile, result);
        verify(retrieveCustomerByEmailUseCase, times(1)).execute();
    }

    @Test
    @DisplayName("Must call service to delete a customer successfully")
    void delete_ShouldCallDeleteCustomerProfileUseCase() {
        customerProfileService.delete(1L);

        verify(deleteCustomerProfileUseCase, times(1)).execute(1L);
    }
}
