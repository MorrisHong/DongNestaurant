package me.grace.dongnestaurant.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuItemRepositoryImpl implements MenuItemRepository {
    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuItemRepositoryImpl() {
        menuItems.add(new MenuItem("김치"));
    }

    @Override
    public List<MenuItem> findAllByRestaurantId(Long restaurantId) {
        return this.menuItems;
    }
}
