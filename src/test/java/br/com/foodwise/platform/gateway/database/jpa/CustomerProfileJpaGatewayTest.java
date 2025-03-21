package br.com.foodwise.platform.gateway.database.jpa;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.gateway.database.jpa.converter.CustomerDomainProfileEntityToDomainConverter;
import br.com.foodwise.platform.gateway.database.jpa.converter.CustomerProfileDomainToEntityConverter;
import br.com.foodwise.platform.gateway.database.jpa.entities.CustomerProfileEntity;
import br.com.foodwise.platform.gateway.database.jpa.repository.CustomerProfileRepository;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.ResourceNotFoundException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerProfileJpaGatewayTest {

    @Mock
    private CustomerProfileRepository customerProfileRepository;

    @Mock
    private CustomerDomainProfileEntityToDomainConverter customerDomainProfileEntityToDomainConverter;

    @Mock
    private CustomerProfileDomainToEntityConverter customerProfileDomainToEntityConverter;

    @InjectMocks
    private CustomerProfileJpaGateway customerProfileJpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find CustomerProfile by id")
    void should_find_CustomerProfile_by_id() {

        long id = Instancio.create(Long.class);
        CustomerProfileEntity entity = Instancio.create(CustomerProfileEntity.class);
        CustomerProfile domain = Instancio.create(CustomerProfile.class);

        when(customerProfileRepository.findById(id)).thenReturn(Optional.of(entity));
        when(customerDomainProfileEntityToDomainConverter.convert(entity)).thenReturn(domain);

        CustomerProfile result = customerProfileJpaGateway.findById(id);

        assertEquals(domain, result);
        verify(customerProfileRepository).findById(id);
        verify(customerDomainProfileEntityToDomainConverter).convert(entity);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when CustomerProfile is not found by id")
    void should_throw_ResourceNotFoundException_when_CustomerProfile_is_not_found_by_id() {

        long id = Instancio.create(Long.class);

        when(customerProfileRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> customerProfileJpaGateway.findById(id));
        verify(customerProfileRepository).findById(id);
    }

    @Test
    @DisplayName("Should find CustomerProfile by user email")
    void should_find_CustomerProfile_by_user_email() {

        String email = Instancio.create(String.class);
        CustomerProfileEntity entity = Instancio.create(CustomerProfileEntity.class);
        CustomerProfile domain = Instancio.create(CustomerProfile.class);

        when(customerProfileRepository.findByUserEntityEmail(email)).thenReturn(Optional.of(entity));
        when(customerDomainProfileEntityToDomainConverter.convert(entity)).thenReturn(domain);

        CustomerProfile result = customerProfileJpaGateway.findByUserEmail(email);

        assertEquals(domain, result);
        verify(customerProfileRepository).findByUserEntityEmail(email);
        verify(customerDomainProfileEntityToDomainConverter).convert(entity);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when CustomerProfile is not found by user email")
    void should_throw_ResourceNotFoundException_when_CustomerProfile_is_not_found_by_user_email() {

        String email = Instancio.create(String.class);

        when(customerProfileRepository.findByUserEntityEmail(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> customerProfileJpaGateway.findByUserEmail(email));
        verify(customerProfileRepository).findByUserEntityEmail(email);
    }

    @Test
    @DisplayName("Should save CustomerProfile")
    void should_save_CustomerProfile() {

        CustomerProfile domain = Instancio.create(CustomerProfile.class);
        CustomerProfileEntity entity = Instancio.create(CustomerProfileEntity.class);

        when(customerProfileDomainToEntityConverter.convert(domain)).thenReturn(entity);

        customerProfileJpaGateway.save(domain);

        verify(customerProfileDomainToEntityConverter).convert(domain);
        verify(customerProfileRepository).save(entity);
    }

    @Test
    @DisplayName("Should log info when customerProfile is null")
    void should_log_info_when_customerProfile_is_null() {

        CustomerProfile customerProfile = null;

        assertThrows(ResourceNotFoundException.class, () -> customerProfileJpaGateway.save(customerProfile));
        verify(customerProfileDomainToEntityConverter, never()).convert(any());
        verify(customerProfileRepository, never()).save(any());
    }

}