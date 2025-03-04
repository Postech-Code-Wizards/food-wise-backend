package br.com.foodwise.platform.rest.converter.customer;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerProfileEntityRequestToEntityConverterTest {
//    @Mock
//    private AddressRequestToDomainConverter addressRequestToDomainConverter;
//
//    @Mock
//    private PhoneRequestToDomainConverter phoneRequestToDomainConverter;
//
//    @InjectMocks
//    private CustomerProfileRequestToDomainConverter converter;
//
//    @Test
//    void convert_shouldMapCustomerProfileRequestToProfileEntity() {
//        var source = buildCustomerProfileRequest();
//
//        when(addressRequestToDomainConverter.convert(any()))
//                .thenReturn(buildAddress());
//
//        when(phoneRequestToDomainConverter.convert(any()))
//                .thenReturn(buildPhone());
//
//        var customerProfile = converter.convert(source);
//
//        verify(addressRequestToDomainConverter).convert(source.getAddress());
//        verify(phoneRequestToDomainConverter).convert(source.getPhone());
//
//        assertNotNull(customerProfile);
//        assertNotNull(customerProfile.getAddressEntity());
//        assertNotNull(customerProfile.getPhoneEntity());
//
//        assertEquals(customerProfile.getAddressEntity().getClass(), AddressEntity.class);
//        assertEquals(customerProfile.getPhoneEntity().getClass(), PhoneEntity.class);
//    }
//
//    @Test
//    void convert_shouldHandleNullFieldsGracefully() {
//        var sourceWithNullFields = new CustomerProfileRequest();
//
//        var result = converter.convert(sourceWithNullFields);
//
//        assertNotNull(result);
//        assertNull(result.getAddressEntity());
//        assertNull(result.getPhoneEntity());
//        assertNull(result.getUserEntity());
//        assertNull(result.getFirstName());
//    }

}
