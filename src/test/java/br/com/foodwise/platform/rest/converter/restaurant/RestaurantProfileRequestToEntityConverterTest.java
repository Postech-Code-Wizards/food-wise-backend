package br.com.foodwise.platform.rest.converter.restaurant;

import br.com.foodwise.platform.domain.entities.Address;
import br.com.foodwise.platform.domain.entities.Phone;
import br.com.foodwise.platform.infrastructure.rest.converter.common.AddressRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.common.PhoneRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.converter.restaurant.RestaurantProfileRequestToEntityConverter;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.restaurant.RestaurantProfileRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.foodwise.platform.factory.EntityFactory.buildAddress;
import static br.com.foodwise.platform.factory.EntityFactory.buildPhone;
import static br.com.foodwise.platform.factory.RequestFactory.buildRestaurantProfileRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantProfileRequestToEntityConverterTest {
    @Mock
    private AddressRequestToEntityConverter addressRequestToEntityConverter;

    @Mock
    private PhoneRequestToEntityConverter phoneRequestToEntityConverter;

    @InjectMocks
    private RestaurantProfileRequestToEntityConverter converter;

    @Test
    void convert_shouldMapRestaurantProfileRequestToEntity() {
        var source = buildRestaurantProfileRequest();

        var address = buildAddress();
        var phone = buildPhone();

        when(addressRequestToEntityConverter.convert(any()))
                .thenReturn(address);
        when(phoneRequestToEntityConverter.convert(any()))
                .thenReturn(phone);

        var response = converter.convert(source);

        verify(addressRequestToEntityConverter).convert(source.getAddress());
        verify(phoneRequestToEntityConverter).convert(source.getPhone());

        assertNotNull(response);
        assertNotNull(response.getAddress());
        assertNotNull(response.getPhone());

        assertEquals(address.getClass(), Address.class);
        assertEquals(phone.getClass(), Phone.class);
        assertEquals(source.getBusinessName(), response.getBusinessName());
        assertEquals(source.getCuisineType(), response.getCuisineType());
    }

    @Test
    void convert_shouldHandleNullFieldsGracefully() {
        var sourceWithNullFields = new RestaurantProfileRequest();

        var response = converter.convert(sourceWithNullFields);

        assertNotNull(response);
        assertNull(response.getAddress());
        assertNull(response.getPhone());
        assertNull(response.getCuisineType());
    }
}
