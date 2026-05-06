package co.com.bancolombia.model.restaurant.gateways;

import co.com.bancolombia.model.restaurant.Restaurant;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
}
