package br.com.foodwise.platform.gateway.database.jpa.converter;

import br.com.foodwise.platform.domain.Address;
import br.com.foodwise.platform.gateway.database.jpa.entities.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressEntityToDomainConverter {

    public Address convert(AddressEntity source) {
        return new Address(
                source.getId(),
                source.getStreet(),
                source.getCity(),
                source.getState(),
                source.getNeighborhood(),
                source.getPostalCode(),
                source.getCountry(),
                source.getLatitude(),
                source.getLongitude(),
                source.getCreatedAt(),
                source.getUpdatedAt()
        );
    }
}
