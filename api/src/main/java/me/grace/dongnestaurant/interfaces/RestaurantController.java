package me.grace.dongnestaurant.interfaces;

import me.grace.dongnestaurant.application.RestaurantService;
import me.grace.dongnestaurant.domain.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin
public class RestaurantController {
    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource)
            throws URISyntaxException {
        Restaurant restaurant = Restaurant.builder()
                .name(resource.getName())
                .address(resource.getAddress())
                .id(resource.getId()).build();
        restaurantService.addRestaurant(restaurant);
        URI url = new URI("/restaurants/" +restaurant.getId());
        return ResponseEntity.created(url).body(restaurant);
    }

    @PatchMapping("/restaurants/{id}")
    public Restaurant update(@PathVariable("id") Long id,
                         @RequestBody Restaurant resource) {
        return restaurantService.updateRestaurant(id, resource.getName(), resource.getAddress());

    }

}
