package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.entities.CustomerProfile;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.converter.customer.CustomerProfileRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConvertToCustomerProfileEntityUseCaseTest {

    @Mock
    private CustomerProfileRequestToEntityConverter customerProfileRequestToEntityConverter;

    @InjectMocks
    private ConvertToCustomerProfileEntityUseCase convertToCustomerProfileEntityUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCustomerProfileWhenConversionIsSuccessful() {
        CustomerProfileRequest request = Instancio.create(CustomerProfileRequest.class);
        CustomerProfile expectedCustomer = Instancio.create(CustomerProfile.class);

        when(customerProfileRequestToEntityConverter.convert(request)).thenReturn(expectedCustomer);

        CustomerProfile result = convertToCustomerProfileEntityUseCase.execute(request);

        assertNotNull(result);
        assertEquals(expectedCustomer, result);
        verify(customerProfileRequestToEntityConverter, times(1)).convert(request);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenConversionFails() {
        CustomerProfileRequest request = Instancio.create(CustomerProfileRequest.class);

        when(customerProfileRequestToEntityConverter.convert(request)).thenReturn(null);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            convertToCustomerProfileEntityUseCase.execute(request);
        });

        assertEquals("CUSTOMER_PROFILE_EXCEPTION", exception.getMessage());
        verify(customerProfileRequestToEntityConverter, times(1)).convert(request);
    }

}