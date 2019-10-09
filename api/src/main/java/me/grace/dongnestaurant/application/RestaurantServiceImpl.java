package me.grace.dongnestaurant.application;

import lombok.NoArgsConstructor;
import me.grace.dongnestaurant.domain.MenuItem;
import me.grace.dongnestaurant.domain.MenuItemRepository;
import me.grace.dongnestaurant.domain.Restaurant;
import me.grace.dongnestaurant.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    private MenuItemRepository menuItemRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItemList(menuItems);
        return restaurant;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }


}
