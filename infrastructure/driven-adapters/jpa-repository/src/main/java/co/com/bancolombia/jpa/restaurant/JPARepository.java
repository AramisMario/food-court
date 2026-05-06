package co.com.bancolombia.jpa;

//import co.com.bancolombia.model.restaurant.Restaurant;
import co.com.bancolombia.jpa.restaurant.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPARepository extends CrudRepository<RestaurantEntity, Integer>, QueryByExampleExecutor<RestaurantEntity> {
}
