package co.com.bancolombia.jpa.dish;

import co.com.bancolombia.model.dish.Dish;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import co.com.bancolombia.jpa.helper.AdapterOperations;
import co.com.bancolombia.jpa.dish.dishEntity.DishEntity;
import co.com.bancolombia.model.dish.gateways.DishRepository;

@Repository
public class JPADishRepositoryAdapter extends AdapterOperations<Dish, DishEntity, Integer, JPADishRepository> implements DishRepository
// implements ModelRepository from domain
{

    public JPADishRepositoryAdapter(JPADishRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Dish.class));
    }
}
