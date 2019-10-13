package me.grace.dongnestaurant.interfaces;

import me.grace.dongnestaurant.application.RestaurantService;
import me.grace.dongnestaurant.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RestaurantRepository restaurantRepository;

    @MockBean
    RestaurantService restaurantService;

    @MockBean
    MenuItemRepository menuItemRepository;
    @Test
    public void 가게_리스트를_JSON으로_뿌려준다() throws Exception {
        List<Restaurant> restaurants = mockGetRestaurants();
        given(restaurantService.getRestaurants()).willReturn(restaurants);
        mockMvc.perform(get("/restaurants"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")))
                .andExpect(content().string(containsString("\"name\":\"Archim\"")))
                .andExpect(content().string(containsString("\"address\":\"Seoul\"")))
                .andExpect(content().string(containsString("\"id\":1,\"name\":\"Archim\",\"address\":\"Seoul\"")))
                .andExpect(content().string(containsString("\"id\":2,\"name\":\"Jeomsim\",\"address\":\"Yong-In\"")));
    }

    private List<Restaurant> mockGetRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1L, "Archim", "Seoul"));
        restaurants.add(new Restaurant(2L, "Jeomsim", "Yong-In"));
        return restaurants;
    }

    @Test
    public void 가게_하나를_JSON으로_뿌려준다() throws Exception {

        final long id = 1004L;
        Restaurant restaurant = Restaurant.builder()
                .id(id)
                .name("Archim")
                .address("Yong-In")
                .build();

        given(restaurantService.getRestaurant(id))
                .willReturn(restaurant);

        Review review = Review.builder()
                .name("grace")
                .score(5)
                .description("대다내!")
                .build();

        MenuItem menuItem = MenuItem.builder()
                .name("Kimchi")
                .build();

        restaurant.setReviews(Arrays.asList(review));
        restaurant.setMenuItemList(Arrays.asList(menuItem));

        mockMvc.perform(get("/restaurants/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004,\"name\":\"Archim\",\"address\":\"Yong-In\"")))
                .andExpect(content().string(containsString("\"name\":\"grace\"")))
                .andExpect(content().string(containsString("\"score\":5")))
                .andExpect(content().string(containsString("\"name\":\"Kimchi\"")))
                .andExpect(content().string(containsString("\"description\":\"대다내!\"")));

    }

    @Test
    public void NotFound_Exception() throws Exception {
        given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));
        mockMvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void created_With_Valid_Data() throws Exception {

        mockMvc.perform(post("/restaurants")
                .content("{\"name\":\"BeRyong\",\"address\":\"Busan\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void created_With_Invalid_Data() throws Exception {

        mockMvc.perform(post("/restaurants")
                .content("{\"name\":\"\",\"address\":\"\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }


    @Test
    public void update_with_valid_data() throws Exception {
        mockMvc.perform(patch("/restaurants/1234")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"joker bar\",\"address\":\"Busan\"}"))
                .andExpect(status().isOk());

        final String name = "joker bar";
        final String address = "Busan";
        verify(restaurantService).updateRestaurant(1234L, name, address);
    }

    @Test
    public void update_with_invalid_data() throws Exception {
        mockMvc.perform(patch("/restaurants/1234")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());


    }
}