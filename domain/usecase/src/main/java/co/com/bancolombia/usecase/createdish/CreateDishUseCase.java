package co.com.bancolombia.usecase.createdish;

import co.com.bancolombia.exceptions.UserNotOwnRestaurantException;
import co.com.bancolombia.model.dish.Dish;
import co.com.bancolombia.model.restaurant.gateways.RestaurantRepository;
import co.com.bancolombia.model.dish.gateways.DishRepository;
import co.com.bancolombia.model.restaurant.Restaurant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateDishUseCase {

    // private final OwnerPort ownerPort;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    public Dish execute(CreateDishCommand createDishCommand) {
        Dish dish = createDishCommand.getDish();
        Integer ownerId = createDishCommand.getOwnerId();
        // Owner owner = ownerPort.verifyOwner(restaurant.getOwnerId());
        // System.out.println("----------OWNER------: "+owner);
        Restaurant restaurant = restaurantRepository.findById(createDishCommand.getRestaurantId());
        if (restaurant == null) {
            throw new RuntimeException("no existe el restaurante");
        }
        if (ownerId != restaurant.getOwnerId()) {
            throw new UserNotOwnRestaurantException();
        }

        dish.setRestaurant(restaurant);
        Dish createdDish =dishRepository.save(dish);

        return createdDish;
    }

}
