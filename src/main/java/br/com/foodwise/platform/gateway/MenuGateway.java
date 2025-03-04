package br.com.foodwise.platform.gateway;

import br.com.foodwise.platform.domain.Menu;

import java.util.List;

public interface MenuGateway {

    Menu findById(long id);

    List<Menu> findAll();

    List<Menu> findByRestaurantProfileEntityBusinessName(String businessName);

    Menu save(Menu menu);

    void delete(Menu menu);

}
