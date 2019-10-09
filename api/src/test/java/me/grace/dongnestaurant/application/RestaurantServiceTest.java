package me.grace.dongnestaurant.application;

import me.grace.dongnestaurant.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class RestaurantServiceTest {

    private RestaurantService restaurantService;
    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp() {
        this.restaurantRepository = new RestaurantRepositoryImpl();
        this.menuItemRepository = new MenuItemRepositoryImpl();
        this.restaurantService = new RestaurantServiceImpl(restaurantRepository, menuItemRepository);
    }
    @Test
    public void restaurant객체를_가져온다() {
        Restaurant restaurant = restaurantService.getRestaurant(1L);
        assertThat(restaurant.getId(),is(1L));
    }

}