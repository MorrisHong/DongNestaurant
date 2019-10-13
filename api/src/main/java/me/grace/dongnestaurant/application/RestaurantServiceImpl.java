package me.grace.dongnestaurant.application;

import me.grace.dongnestaurant.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    private MenuItemRepository menuItemRepository;
    private ReviewRepository reviewRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItemList(menuItems);
        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
        restaurant.setReviews(reviews);
        return restaurant;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public Restaurant updateRestaurant(long id, String name, String address) {
        //TODO: update Restaurant.
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        assert restaurant != null;
        restaurant.updateInfo(name, address);


        return restaurant;
    }
}
