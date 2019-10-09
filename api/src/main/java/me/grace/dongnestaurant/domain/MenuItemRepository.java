package me.grace.dongnestaurant.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findAllByRestaurantId(Long restaurantId);

}
