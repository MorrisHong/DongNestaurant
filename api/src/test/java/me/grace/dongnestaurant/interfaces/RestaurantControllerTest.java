package me.grace.dongnestaurant.interfaces;

import me.grace.dongnestaurant.application.RestaurantService;
import me.grace.dongnestaurant.application.RestaurantServiceImpl;
import me.grace.dongnestaurant.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    //todo:spybean대신 mockBean으로 바꿔줘보자.
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
        restaurants.add(new Restaurant(1L, "Archim", "Seoul", new ArrayList<>()));
        restaurants.add(new Restaurant(2L, "Jeomsim", "Yong-In", new ArrayList<>()));
        return restaurants;
    }

    @Test
    public void 가게_하나를_JSON으로_뿌려준다() throws Exception {
        Long id = 2L;
        given(restaurantService.getRestaurant(id))
                .willReturn(mockGetRestaurants()
                        .stream()
                        .filter(restaurant -> restaurant.getId().equals(id)).findFirst().get());
        mockMvc.perform(get("/restaurants/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2,\"name\":\"Jeomsim\",\"address\":\"Yong-In\"")));
//                .andExpect(content().string(containsString("김치")));
    }

    @Test
    public void created() throws Exception {

        mockMvc.perform(post("/restaurants")
                    .content("{\"id\":1234,\"name\":\"BeRyong\",\"address\":\"Busan\"}")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }
}