package br.com.foodwise.platform.infrastructure.rest.converter.customer;

import br.com.foodwise.platform.domain.CustomerProfile;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.CustomerProfileResponse;
import org.instancio.Instancio;
import org.instancio.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerProfileDomainToResponseConverterTest {

    @InjectMocks
    private CustomerProfileDomainToResponseConverter converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should convert CustomerProfile to CustomerProfileResponse correctly")
    void convert_ShouldConvertCustomerProfileToCustomerProfileResponse() {

        CustomerProfile source = Instancio.create(CustomerProfile.class);

        CustomerProfileResponse result = converter.convert(source);

        assertEquals(source.getFirstName(), result.getFirstName());
        assertEquals(source.getLastName(), result.getLastName());


    }

    @Test
    @DisplayName("Should convert CustomerProfile with null fields")
    void convert_ShouldConvertCustomerProfileWithNullFields() {

        Model<CustomerProfile> customerProfileModel = Instancio.of(CustomerProfile.class)
                .set(field(CustomerProfile::getId), null)
                .set(field(CustomerProfile::getAddress), null)
                .set(field(CustomerProfile::getCreatedAt), null)
                .set(field(CustomerProfile::getUpdatedAt), null)
                .set(field(CustomerProfile::getUser), null)
                .set(field(CustomerProfile::getPhone), null)
                .toModel();

        CustomerProfile source = Instancio.create(customerProfileModel);

        assertNull(source.getId());
        assertNull(source.getAddress());
        assertNull(source.getCreatedAt());
        assertNull(source.getUpdatedAt());
        assertNull(source.getUser());
        assertNull(source.getPhone());

    }

}