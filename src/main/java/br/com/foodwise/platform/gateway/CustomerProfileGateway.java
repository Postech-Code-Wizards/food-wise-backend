package br.com.foodwise.platform.gateway;

import br.com.foodwise.platform.domain.CustomerProfile;

public interface CustomerProfileGateway {

    CustomerProfile findById(long id);

    CustomerProfile findByUserEmail(String email);

    void save(CustomerProfile customerProfile);

}
