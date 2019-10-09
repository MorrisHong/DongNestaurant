package me.grace.dongnestaurant.interfaces;

import me.grace.dongnestaurant.application.RestaurantService;
import me.grace.dongnestaurant.application.RestaurantServiceImpl;
import me.grace.dongnestaurant.domain.MenuItemRepository;
import me.grace.dongnestaurant.domain.MenuItemRepositoryImpl;
import me.grace.dongnestaurant.domain.RestaurantRepository;
import me.grace.dongnestaurant.domain.RestaurantRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
//todo:spybean대신 mockBean으로 바꿔줘보자.
    @Autowired
    MockMvc mockMvc;

    @SpyBean(RestaurantRepositoryImpl.class)
    RestaurantRepository restaurantRepository;

    @SpyBean(RestaurantServiceImpl.class)
    RestaurantService restaurantService;

    @SpyBean(MenuItemRepositoryImpl.class)
    MenuItemRepository menuItemRepository;

    @Test
    public void 가게_리스트를_JSON으로_뿌려준다() throws Exception {
        mockMvc.perform(get("/restaurants"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")))
                .andExpect(content().string(containsString("\"name\":\"Archim\"")))
                .andExpect(content().string(containsString("\"address\":\"Seoul\"")))
                .andExpect(content().string(containsString("\"id\":1,\"name\":\"Archim\",\"address\":\"Seoul\"")))
                .andExpect(content().string(containsString("\"id\":2,\"name\":\"Jeomsim\",\"address\":\"Yong-In\"")));
    }

    @Test
    public void 가게_하나를_JSON으로_뿌려준다() throws Exception {
        Long id = 2L;
        mockMvc.perform(get("/restaurants/"+id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2,\"name\":\"Jeomsim\",\"address\":\"Yong-In\"")))
                .andExpect(content().string(containsString("김치")));
    }
}