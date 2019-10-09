package me.grace.dongnestaurant.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> list;

    public RestaurantRepositoryImpl() {
        list = new ArrayList<>();
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

        list.add(restaurant1);
        list.add(restaurant2);
    }

    @Override
    public List<Restaurant> findAll() {
        return this.list;
    }

    @Override
    public Restaurant findById(Long id) {
        for (Restaurant restaurant : list) {
            if (restaurant.getId().equals(id)) return restaurant;
        }
        return null;
    }
}
