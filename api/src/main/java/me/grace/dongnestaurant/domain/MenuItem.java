package me.grace.dongnestaurant.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class MenuItem {
    @Id @GeneratedValue
    private Long id;

    private Long restaurantId;

    private String name;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean destroy;


    public MenuItem(String name) {
        this.name = name;
    }

    @Builder
    public MenuItem(Long id, Long restaurantId, String name, boolean destroy) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.destroy = destroy;
    }
}
