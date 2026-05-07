package co.com.bancolombia.jpa.restaurant;

//import co.com.bancolombia.model.restaurant.Restaurant;
import co.com.bancolombia.jpa.restaurant.restaurantEntity.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPARestaurantRepository extends CrudRepository<RestaurantEntity, Integer>, QueryByExampleExecutor<RestaurantEntity> {
}
