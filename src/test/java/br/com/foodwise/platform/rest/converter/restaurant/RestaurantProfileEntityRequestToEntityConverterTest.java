package br.com.foodwise.platform.rest.converter.restaurant;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RestaurantProfileEntityRequestToEntityConverterTest {
//    @Mock
//    private AddressRequestToDomainConverter addressRequestToDomainConverter;
//
//    @Mock
//    private PhoneRequestToDomainConverter phoneRequestToDomainConverter;
//
//    @InjectMocks
//    private RestaurantProfileRequestToDomainConverter converter;
//
//    @Test
//    void convert_shouldMapRestaurantProfileRequestToEntity() {
//        var source = buildRestaurantProfileRequest();
//
//        var address = buildAddress();
//        var phone = buildPhone();
//
//        when(addressRequestToDomainConverter.convert(any()))
//                .thenReturn(address);
//        when(phoneRequestToDomainConverter.convert(any()))
//                .thenReturn(phone);
//
//        var response = converter.convert(source);
//
//        verify(addressRequestToDomainConverter).convert(source.getAddress());
//        verify(phoneRequestToDomainConverter).convert(source.getPhone());
//
//        assertNotNull(response);
//        assertNotNull(response.getAddressEntity());
//        assertNotNull(response.getPhoneEntity());
//
//        assertEquals(address.getClass(), AddressEntity.class);
//        assertEquals(phone.getClass(), PhoneEntity.class);
//        assertEquals(source.getBusinessName(), response.getBusinessName());
//        assertEquals(source.getCuisineType(), response.getCuisineType());
//    }
//
//    @Test
//    void convert_shouldHandleNullFieldsGracefully() {
//        var sourceWithNullFields = new RestaurantProfileRequest();
//
//        var response = converter.convert(sourceWithNullFields);
//
//        assertNotNull(response);
//        assertNull(response.getAddressEntity());
//        assertNull(response.getPhoneEntity());
//        assertNull(response.getCuisineType());
//    }
}
