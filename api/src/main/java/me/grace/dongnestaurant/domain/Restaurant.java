package me.grace.dongnestaurant.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private List<MenuItem> menuItemList;

    @Builder
    public Restaurant(Long id, String name, String address, List menuItemList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.menuItemList = menuItemList;
    }

}
