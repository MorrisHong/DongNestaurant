package me.grace.dongnestaurant.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItem {
    private String name;

    public MenuItem(String name) {
        this.name = name;
    }


}
