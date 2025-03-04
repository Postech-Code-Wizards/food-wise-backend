package br.com.foodwise.platform.rest.converter.customer;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerProfileEntityEntityToResponseConverterTest {

//    private CustomerProfileDomainToResponseConverter converter;
//
//    @BeforeEach
//    void setUp() {
//        converter = new CustomerProfileDomainToResponseConverter();
//    }
//
//    @Test
//    void convert_shouldMapCustomerProfileToCustomerProfileResponse() {
//        var source = buildCustomerProfile();
//
//        var result = converter.convert(source);
//
//        assertNotNull(result);
//        assertEquals(source.getFirstName(), result.getFirstName());
//        assertEquals(source.getLastName(), result.getLastName());
//        assertEquals(source.getPhoneEntity(), result.getPhoneEntity());
//        assertEquals(source.getAddressEntity(), result.getAddressEntity());
//    }
//
//    @Test
//    void convert_shouldHandleNullFieldsGracefully() {
//        var sourceWithNullFields = new CustomerProfileEntity();
//        var result = converter.convert(sourceWithNullFields);
//
//        assertNotNull(result);
//        assertNull(result.getFirstName());
//        assertNull(result.getLastName());
//        assertNull(result.getAddressEntity());
//        assertNull(result.getPhoneEntity());
//    }
}
