package co.com.bancolombia.usecase.updateDish;

import java.util.List;

import co.com.bancolombia.exceptions.UserNotOwnRestaurantException;
import co.com.bancolombia.model.dish.Dish;
import co.com.bancolombia.model.dish.gateways.DishRepository;
import co.com.bancolombia.model.restaurant.Restaurant;
import co.com.bancolombia.model.restaurant.gateways.RestaurantRepository;
import co.com.bancolombia.usecase.updateDish.UpdateDishCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class UpdateDishUseCase {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public Dish execute(UpdateDishCommand updateDishCommand) {

        List<String> createpermission = List.of("Owner");

        if (!createpermission.contains(updateDishCommand.getUserAuthenticatedRole())) {
            throw new RuntimeException(
                    "EL USUARIO NO TIENE PERMISOS PARA ACTUALIZAR PLATOS POR QUE NO ES OWNER");
        }

        Dish dishInfoToUpdate = updateDishCommand.getDish();
        Dish dishToUpdate = dishRepository.findById(updateDishCommand.getDishId());
        Restaurant restaurant = restaurantRepository.findById(dishToUpdate.getRestaurant().getId());

        if (updateDishCommand.getOwnerId() != restaurant.getOwnerId()) {
            throw new UserNotOwnRestaurantException();
        }

        dishToUpdate.setPrice(dishInfoToUpdate.getPrice());
        dishToUpdate.setDescription(dishInfoToUpdate.getDescription());
        dishToUpdate.setActive(dishInfoToUpdate.getActive());

        return dishRepository.save(dishToUpdate);
    }

}
