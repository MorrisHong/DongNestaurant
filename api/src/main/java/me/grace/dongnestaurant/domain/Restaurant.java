package me.grace.dongnestaurant.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;


    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuItem> menuItemList;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Review> reviews;

    @Builder
    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = new ArrayList<>(reviews);
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = new ArrayList<>(menuItemList);
    }

    public void updateInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
