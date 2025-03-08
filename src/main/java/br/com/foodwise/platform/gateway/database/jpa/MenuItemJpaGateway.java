//package br.com.foodwise.platform.gateway.database.jpa;
//
//import br.com.foodwise.platform.domain.MenuItem;
//import br.com.foodwise.platform.gateway.MenuItemGateway;
//import br.com.foodwise.platform.gateway.database.jpa.repository.MenuItemRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class MenuItemJpaGateway implements MenuItemGateway {
//
//    private final MenuItemRepository menuItemRepository;
//
//    @Override
//    public MenuItem findById(Long id) {
//        return null;
//        // .orElseThrow(() -> new ResourceNotFoundException("Menu Item"))
//    }
//
//    @Override
//    public List<MenuItem> findAll() {
//        return List.of();
//    }
//
//    @Override
//    public List<MenuItem> findMenuItemByName(String itemName) {
//        return List.of();
//    }
//
//    @Override
//    public MenuItem save(MenuItem menuItem) {
//        return null;
//    }
//
//    @Override
//    public void delete(MenuItem menuItem) {
//
//    }
//}
