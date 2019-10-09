package me.grace.dongnestaurant.application;

import me.grace.dongnestaurant.domain.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant getRestaurant(Long id);

    List<Restaurant> getRestaurants();
}
