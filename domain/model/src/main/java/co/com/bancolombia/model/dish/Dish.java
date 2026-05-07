package co.com.bancolombia.model.dish;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import co.com.bancolombia.model.restaurant.Restaurant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Dish {
    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private String urlImage;
    private String category;
    @Builder.Default
    private Boolean active = true;
    private Restaurant restaurant;
}
