package co.com.bancolombia.jpa.dish.dishEntity;

import co.com.bancolombia.jpa.restaurant.restaurantEntity.RestaurantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dishes")
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String description;

    private String urlImage;

    private String category;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "idRestaurant")
    private RestaurantEntity restaurant;
}
