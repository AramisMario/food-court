package co.com.bancolombia.usecase.createrestaurant;
//import co.com.bancolombia.model.owner.Owner;
import co.com.bancolombia.model.restaurant.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class CreateRestaurantCommand{
    private Restaurant restaurant;
    private String userAuthenticatedRole; 
}