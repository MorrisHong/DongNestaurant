package me.grace.dongnestaurant.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RestaurantTest {

    @Test
    public void created() {
        Long id = 1L;
        String name = "Archim";
        String address = "Yong-In";
        Restaurant restaurant = Restaurant.builder()
                .id(id)
                .name(name)
                .address(address).build();
        assertThat(restaurant.getId(), is(1L));
        assertThat(restaurant.getName(), is("Archim"));
        assertThat(restaurant.getAddress(), is("Yong-In"));
    }

}