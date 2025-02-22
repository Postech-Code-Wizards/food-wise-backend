package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.application.usecase.user.CreateUserUseCase;
import br.com.foodwise.platform.domain.entities.CustomerProfile;
import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.domain.entities.enums.UserType;
import br.com.foodwise.platform.domain.repository.CustomerProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.foodwise.platform.factory.RequestFactory.buildValidRegisterCustomerRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegisterCustomerUseCaseTest {

    @Mock
    private CustomerProfileRepository customerProfileRepository;

    @Mock
    private ConvertToCustomerProfileEntityUseCase convertToCustomerProfileEntityUseCase;

    @Mock
    private CreateUserUseCase createUserUseCase;

    @InjectMocks
    private RegisterCustomerUseCase registerCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterCustomerSuccessfully() {
        var registerCustomerRequest = buildValidRegisterCustomerRequest();
        var userRequest = registerCustomerRequest.getUser();
        var user = new User();
        when(createUserUseCase.execute(userRequest.getEmail(), userRequest.getPassword(), UserType.CUSTOMER))
                .thenReturn(user);

        var customerEntity = new CustomerProfile();
        when(convertToCustomerProfileEntityUseCase.execute(any()))
                .thenReturn(customerEntity);

        registerCustomerUseCase.execute(registerCustomerRequest);

        verify(customerProfileRepository).save(customerEntity);
    }

}