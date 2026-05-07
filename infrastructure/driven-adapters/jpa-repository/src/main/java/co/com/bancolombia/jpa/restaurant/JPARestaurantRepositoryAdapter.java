package co.com.bancolombia.jpa.restaurant;

import co.com.bancolombia.jpa.restaurant.JPARestaurantRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import co.com.bancolombia.model.restaurant.Restaurant;
import co.com.bancolombia.jpa.helper.AdapterOperations;
import co.com.bancolombia.jpa.restaurant.restaurantEntity.RestaurantEntity;
import co.com.bancolombia.model.restaurant.gateways.RestaurantRepository;

@Repository
public class JPARestaurantRepositoryAdapter extends AdapterOperations<Restaurant, RestaurantEntity, Integer, JPARestaurantRepository> implements RestaurantRepository
// implements ModelRepository from domain
{

    public JPARestaurantRepositoryAdapter(JPARestaurantRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Restaurant.class));
    }
}
