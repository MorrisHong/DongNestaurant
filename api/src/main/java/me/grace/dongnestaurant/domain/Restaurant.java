package me.grace.dongnestaurant.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Restaurant {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String address;
    @Transient
    private List<MenuItem> menuItemList;

    @Builder
    public Restaurant(Long id, String name, String address, List menuItemList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.menuItemList = menuItemList;
    }

}
