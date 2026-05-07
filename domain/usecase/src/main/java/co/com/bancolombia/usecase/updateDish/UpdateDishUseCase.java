package co.com.bancolombia.usecase.updateDish;
import co.com.bancolombia.model.dish.Dish;
import co.com.bancolombia.model.dish.gateways.DishRepository;
import co.com.bancolombia.usecase.updateDish.UpdateDishCommand;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor

public class UpdateDishUseCase {
    private final DishRepository dishRepository;

    public Dish execute(UpdateDishCommand updateDishCommand) {
        Dish dishInfoToUpdate = updateDishCommand.getDish();
        Dish dishToUpdate = dishRepository.findById(updateDishCommand.getDishId());

        dishToUpdate.setPrice(dishInfoToUpdate.getPrice());
        dishToUpdate.setDescription(dishInfoToUpdate.getDescription());

        return dishRepository.save(dishToUpdate);
    }
    
}
