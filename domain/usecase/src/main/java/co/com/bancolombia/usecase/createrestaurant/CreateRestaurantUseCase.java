package co.com.bancolombia.usecase.createrestaurant;
import co.com.bancolombia.model.owner.gateways.OwnerPort;
import co.com.bancolombia.model.restaurant.Restaurant;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class CreateRestaurantUseCase {

    private final OwnerPort ownerPort;


    public Restaurant execute(CreateRestaurantCommand createRestaurantCommand){
        Restaurant restaurant = createRestaurantCommand.getRestaurant();
        ownerPort.getOwner(restaurant.getOwnerId());
        return restaurant;
    }

}
