package co.com.bancolombia.usecase.createdish;
//import co.com.bancolombia.model.owner.Owner;
import co.com.bancolombia.model.dish.Dish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateDishCommand{
    private Dish dish;
    private Integer restaurantId;
    private Integer ownerId;
}