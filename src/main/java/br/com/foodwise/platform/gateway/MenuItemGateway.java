package br.com.foodwise.platform.gateway;

import br.com.foodwise.platform.domain.MenuItem;

import java.util.List;

public interface MenuItemGateway {

    MenuItem findById(Long id);

    List<MenuItem> findAll();

    List<MenuItem> findMenuItemByName(String itemName);

    MenuItem save(MenuItem menuItem);

    void delete(MenuItem menuItem);

}
