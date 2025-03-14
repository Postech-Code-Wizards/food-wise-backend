package br.com.foodwise.platform.application.usecase.restaurant;

import br.com.foodwise.platform.domain.RestaurantProfile;
import br.com.foodwise.platform.gateway.RestaurantProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveRestaurantByIdUseCase {
     private RestaurantProfileGateway restaurantProfileGateway;

     public RestaurantProfile execute(Long id){
         return restaurantProfileGateway.findById(id);
     }



}
