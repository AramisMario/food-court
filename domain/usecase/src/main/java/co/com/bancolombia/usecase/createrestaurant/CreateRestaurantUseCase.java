package co.com.bancolombia.usecase.createrestaurant;
import co.com.bancolombia.model.owner.gateways.OwnerPort;
import co.com.bancolombia.model.restaurant.Restaurant;
import co.com.bancolombia.model.restaurant.gateways.RestaurantRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class CreateRestaurantUseCase {

    private final OwnerPort ownerPort;
    private final RestaurantRepository restaurantRepository;


    public Restaurant execute(CreateRestaurantCommand createRestaurantCommand){
        Restaurant restaurant = createRestaurantCommand.getRestaurant();
        ownerPort.getOwner(restaurant.getOwnerId());
        Restaurant restaurantSaved = restaurantRepository.save(restaurant);
        return restaurantSaved;
    }

}
