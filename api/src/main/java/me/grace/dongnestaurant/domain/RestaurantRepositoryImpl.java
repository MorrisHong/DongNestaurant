package me.grace.dongnestaurant.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurants;

    public RestaurantRepositoryImpl() {
        restaurants = new ArrayList<>();
//        Restaurant restaurant = new Restaurant(1L,"Archim","Seoul", new ArrayList());
        Restaurant restaurant1 = Restaurant.builder()
                .id(1L)
                .name("Archim")
                .address("Seoul")
                .build();
        Restaurant restaurant2 = Restaurant.builder()
                .id(2L)
                .name("Jeomsim")
                .address("Yong-In")
                .build();

        restaurants.add(restaurant1);
        restaurants.add(restaurant2);
    }

    @Override
    public List<Restaurant> findAll() {
        return this.restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId().equals(id)) return restaurant;
        }
        return null;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        restaurant.setId(1234L);
        restaurants.add(restaurant);
        return restaurant;
    }
}
