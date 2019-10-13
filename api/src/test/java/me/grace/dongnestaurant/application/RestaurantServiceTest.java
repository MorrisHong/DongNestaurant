package me.grace.dongnestaurant.application;

import me.grace.dongnestaurant.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantService restaurantService;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private MenuItemRepository menuItemRepository;
    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
//        this.restaurantRepository = new RestaurantRepositoryImpl();
//        this.menuItemRepository = new MenuItemRepositoryImpl();
//        this.restaurantService = new RestaurantServiceImpl(restaurantRepository, menuItemRepository);
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();

        restaurantService = new RestaurantServiceImpl(restaurantRepository, menuItemRepository, reviewRepository);
    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        Review review = Review.builder()
                .name("grace")
                .score(5)
                .description("마시써!")
                .restaurantId(1L)
                .build();
        reviews.add(review);
        given(reviewRepository.findAllByRestaurantId(1L)).willReturn(reviews);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .name("Archim")
                .address("Seoul")
                .build();
        restaurants.add(restaurant);
        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1L)).willReturn(Optional.ofNullable(restaurant));

    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Kimchi"));
        given(menuItemRepository.findAllByRestaurantId(1L)).willReturn(menuItems);
    }


    @Test
    public void restaurant객체를_가져온다() {
        Restaurant restaurant = restaurantService.getRestaurant(1L);
        MenuItem menuItem = menuItemRepository.findAllByRestaurantId(1L).get(0);
        Review review = reviewRepository.findAllByRestaurantId(1L).get(0);

        verify(restaurantRepository).findById(eq(1L));
        verify(menuItemRepository, times(2)).findAllByRestaurantId(eq(1L));
        verify(reviewRepository, times(2)).findAllByRestaurantId(eq(1L));

        assertThat(restaurant.getId(), is(1L));
        assertThat(menuItem.getName(),is("Kimchi"));
        assertThat(review.getDescription(), is("마시써!"));
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void notFoundRestaurant() {
        restaurantService.getRestaurant(404L);
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> all = restaurantRepository.findAll();
        Restaurant restaurant = all.get(0);
        assertThat(restaurant.getName(),is("Archim"));
    }

    @Test
    public void addRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                .name("ByRyong")
                .address("Busan").build();

        Restaurant saved = Restaurant.builder()
                .id(1234L)
                .name("BeRyong")
                .address("Busan")
                .build();
        given(restaurantRepository.save(any())).willReturn(saved);

        Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);

        assertThat(createdRestaurant.getId(), is(1234L));

    }

    @Test
    public void updateRestaurant() {
        Restaurant originRestaurant = Restaurant.builder()
                .id(1L)
                .name("Archim")
                .address("Seoul")
                .build();

        given(restaurantRepository.findById(1L)).willReturn(Optional.ofNullable(originRestaurant));

        Restaurant updatedRestaurant = restaurantService.updateRestaurant(originRestaurant.getId(), "SoolPub","Busan");

        assertThat(updatedRestaurant.getName(), is("SoolPub"));
        assertThat(updatedRestaurant.getAddress(), is("Busan"));

    }

}