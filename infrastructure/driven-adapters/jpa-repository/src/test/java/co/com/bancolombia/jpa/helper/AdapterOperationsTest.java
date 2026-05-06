package co.com.bancolombia.jpa.helper;

import co.com.bancolombia.jpa.JPARepository;
import co.com.bancolombia.jpa.JPARepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import co.com.bancolombia.model.restaurant.Restaurant;
import co.com.bancolombia.jpa.restaurant.RestaurantEntity;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AdapterOperationsTest {

    @Mock
    private JPARepository repository;

    @Mock
    private ObjectMapper objectMapper;

    private JPARepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(objectMapper.map("value", Object.class)).thenReturn("value");

        adapter = new JPARepositoryAdapter(repository, objectMapper);
    }

    private Restaurant buildRestaurant() {
        return Restaurant.builder()
                .id(3)
                .name("name")
                .taxIdentification("12345678")
                .addres("Calle 42#23-98 Local 123")
                .phone("3456237")
                .urlLogo("path del logo")
                .ownerId(57)
                .build();
    }

    private RestaurantEntity buildRestaurantEntity() {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(3);
        restaurantEntity.setName("name");
        restaurantEntity.setTaxIdentification("12345678");
        restaurantEntity.setAddres("Calle 42#23-98 Local 123");
        restaurantEntity.setPhone("3456237");
        restaurantEntity.setUrlLogo("path del logo");
        restaurantEntity.setOwnerId(57);
        return restaurantEntity;
    }

    @Test
    void testSave() {

        when(objectMapper.map(any(), any())).thenReturn(this.buildRestaurantEntity()).thenReturn(this.buildRestaurant());
        when(repository.save(any())).thenReturn(this.buildRestaurantEntity());

        Restaurant restaurant = this.buildRestaurant();

        Restaurant result = adapter.save(restaurant);
        assertEquals(result.getId(), restaurant.getId());
        assertEquals(result.getName(), restaurant.getName());
        assertEquals(result.getPhone(), restaurant.getPhone());
    }

    @Test
    void testSaveAllEntities() {

        Restaurant restaurant = this.buildRestaurant();
        RestaurantEntity restaurantEntity = this.buildRestaurantEntity();

        List<Restaurant> objectValues = List.of(restaurant);
        List<RestaurantEntity> entityValues = List.of(restaurantEntity);

        when(objectMapper.map(any(), any())).thenReturn(restaurantEntity).thenReturn(restaurant);
        when(repository.saveAll(entityValues)).thenReturn(entityValues);

        List<Restaurant> result = adapter.saveAllEntities(objectValues);

        assertEquals(result.get(0).getId(), objectValues.get(0).getId());
    }

    @Test
    void testFindById() {

        Restaurant restaurant = this.buildRestaurant();
        RestaurantEntity restaurantEntity = this.buildRestaurantEntity();

        when(objectMapper.map(any(), any())).thenReturn(restaurant);
        when(repository.save(any())).thenReturn(restaurantEntity);

        when(repository.findById(1)).thenReturn(Optional.of(restaurantEntity));

        Object result = adapter.findById(1);

        assertEquals(result, restaurant);
    }

    @Test
    void testFindAll() {
        Restaurant restaurant = this.buildRestaurant();
        RestaurantEntity restaurantEntity = this.buildRestaurantEntity();

        List<Restaurant> restaurants = List.of(restaurant);
        List<RestaurantEntity> restaurantEntities = List.of(restaurantEntity);

        when(objectMapper.map(any(), any())).thenReturn(this.buildRestaurant());
        when(repository.findAll()).thenReturn(restaurantEntities);


        List<Restaurant> result = adapter.findAll();

        assertEquals(result.get(0).getId(), restaurants.get(0).getId());
    }

    @Test
    void testFindByExample() {

        Restaurant restaurant = Restaurant.builder().name("name").build();

        List<RestaurantEntity> restaurantEntities = List.of(this.buildRestaurantEntity());

        when(objectMapper.map(any(), any())).thenReturn(this.buildRestaurant());
        when(repository.findAll(any(Example.class))).thenReturn(restaurantEntities);

        List<Restaurant> result = adapter.findByExample(restaurant);

        assertEquals(result.get(0).getName(), restaurant.getName());
    }
}
