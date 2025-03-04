package br.com.foodwise.platform.infrastructure.rest.converter.common;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.AddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressRequestToDomainConverter {

    public Address convert(AddressRequest source) {
        return new Address(
                null,
                source.getStreet(),
                source.getCity(),
                source.getState(),
                source.getNeighborhood(),
                source.getPostalCode(),
                source.getCountry(),
                source.getLatitude(),
                source.getLongitude(),
                null,
                null
        );
    }

}
