package co.com.bancolombia.jpa.dish;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import co.com.bancolombia.jpa.dish.dishEntity.DishEntity;

public interface JPADishRepository extends CrudRepository<DishEntity, Integer>, QueryByExampleExecutor<DishEntity> {
}
