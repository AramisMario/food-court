package co.com.bancolombia.usecase.updateDish;

import co.com.bancolombia.model.dish.Dish;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateDishCommand {
    private Integer dishId;
    private Dish dish;
}
