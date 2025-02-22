package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.infrastructure.rest.converter.customer.CustomerProfileEntityToResponseConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ConvertToCustomerProfileResponseUseCaseTest {

    @Mock
    private CustomerProfileEntityToResponseConverter customerProfileEntityToResponseConverter;

    @InjectMocks
    private ConvertToCustomerProfileResponseUseCase convertToCustomerProfileResponseUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCustomerProfileWhenConversionIsSuccessful() {
        CustomerProfileResponse expectedCustomerProfileResponse = Instancio.create(CustomerProfileResponse.class);

        when(customerProfileEntityToResponseConverter.convert(any())).thenReturn(expectedCustomerProfileResponse);

        CustomerProfileResponse result = convertToCustomerProfileResponseUseCase.execute(any());

        assertNotNull(result);
        assertEquals(expectedCustomerProfileResponse, result);
        verify(customerProfileEntityToResponseConverter, times(1)).convert(any());
    }

}