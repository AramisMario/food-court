package co.com.bancolombia.jpa;

import co.com.bancolombia.jpa.JPARepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import co.com.bancolombia.model.restaurant.Restaurant;
import co.com.bancolombia.jpa.helper.AdapterOperations;
import co.com.bancolombia.jpa.restaurant.RestaurantEntity;
import co.com.bancolombia.model.restaurant.gateways.RestaurantRepository;

@Repository
public class JPARepositoryAdapter extends AdapterOperations<Restaurant, RestaurantEntity, Integer, JPARepository> implements RestaurantRepository
// implements ModelRepository from domain
{

    public JPARepositoryAdapter(JPARepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Restaurant.class));
    }
}
