package br.com.foodwise.platform.application.usecase.customer;

import br.com.foodwise.platform.domain.entities.CustomerProfile;
import br.com.foodwise.platform.domain.repository.CustomerProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.customer.CustomerProfileRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Optional;

import static br.com.foodwise.platform.factory.RequestFactory.buildCustomerProfileEntity;
import static br.com.foodwise.platform.factory.RequestFactory.buildCustomerProfileRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UpdateCustomerProfileUseCaseTest {

    @Mock
    private CustomerProfileRepository customerProfileRepository;

    @Mock
    private ConvertToCustomerProfileEntityUseCase convertToCustomerProfileEntityUseCase;

    @InjectMocks
    private UpdateCustomerProfileUseCase updateCustomerProfileUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success case for Customer Profile Update")
    void shouldUpdateCustomerProfileSuccessfully() {
        CustomerProfileRequest customerNewData = buildCustomerProfileRequest();
        var customerProfile = buildCustomerProfileEntity();

        when(customerProfileRepository.findById(anyLong())).thenReturn(Optional.of(customerProfile));

        var customerProfileEntity = new CustomerProfile();
        customerProfileEntity.setUpdatedAt(ZonedDateTime.now());
        when(convertToCustomerProfileEntityUseCase.execute(any())).thenReturn(customerProfileEntity);

        updateCustomerProfileUseCase.execute(customerNewData, anyLong());

        verify(customerProfileRepository, times(1)).findById(anyLong());
        assertEquals(customerProfileEntity.getFirstName(), customerProfile.getFirstName());
        assertEquals(customerProfileEntity.getLastName(), customerProfile.getLastName());
        assertEquals(customerProfileEntity.getAddress(), customerProfile.getAddress());
        assertEquals(customerProfileEntity.getPhone(), customerProfile.getPhone());
        assertNotNull(customerProfileEntity.getUpdatedAt());
    }

    @Test
    @DisplayName("Fail case for Customer Profile Update")
    void shouldThrowExceptionForNotFindingCustomerProfile() {
        CustomerProfileRequest customerNewData = buildCustomerProfileRequest();

        long nonExistentUserId = 500000000L;

        doReturn(Optional.empty()).when(customerProfileRepository).findById(nonExistentUserId);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> updateCustomerProfileUseCase.execute(customerNewData, nonExistentUserId)
        );

        assertEquals("CUSTOMER_DOES_NOT_EXIST", exception.getCode());

        verify(customerProfileRepository, times(1)).findById(nonExistentUserId);

        verify(customerProfileRepository, never()).save(any());
    }

}