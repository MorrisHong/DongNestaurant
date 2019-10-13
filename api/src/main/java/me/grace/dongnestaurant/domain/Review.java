package me.grace.dongnestaurant.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
public class Review {
    @Id @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @Max(5) @Min(0)
    private Integer score;

    @NotEmpty
    private String description;

    @Setter
    private Long restaurantId;

    @Builder
    public Review(Long id, String name, Integer score, String description, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.description = description;
        this.restaurantId = restaurantId;
    }

}
