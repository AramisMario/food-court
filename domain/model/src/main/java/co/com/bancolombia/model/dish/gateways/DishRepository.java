package co.com.bancolombia.model.dish.gateways;

import co.com.bancolombia.model.dish.Dish;

public interface DishRepository {
    Dish save(Dish restaurant);
}
