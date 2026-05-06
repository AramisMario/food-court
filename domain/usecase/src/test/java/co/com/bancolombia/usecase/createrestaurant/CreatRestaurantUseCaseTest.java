package co.com.bancolombia.usecase.createrestaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import co.com.bancolombia.model.owner.Owner;
import co.com.bancolombia.model.restaurant.Restaurant;
import co.com.bancolombia.model.restaurant.gateways.RestaurantRepository;
import co.com.bancolombia.model.owner.gateways.OwnerPort;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

public class CreatRestaurantUseCaseTest {


    @Mock
    private OwnerPort ownerPort;

    @Mock
    private RestaurantRepository restaurantRepository;

    private CreateRestaurantUseCase createRestaurantUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        createRestaurantUseCase = new CreateRestaurantUseCase(ownerPort,restaurantRepository);
    }

    @Test
    void testExecute() {

        Restaurant restaurant = Restaurant.builder()
                .id(3)
                .name("name")
                .taxIdentification("12345678")
                .addres("Calle 42#23-98 Local 123")
                .phone("3456237")
                .urlLogo("path del logo")
                .ownerId(57)
                .build();

        Owner owner = Owner.builder()
                .id(57)
                .name("michael")
                .lastName("lastName")
                .email("email@gmail.com")
                .identificationDocument("12345678")
                .birthDate("1998-06-14")
                .phone("3456237")
                .build();

        when(restaurantRepository.save(any())).thenReturn(restaurant);
        when(ownerPort.verifyOwner(any())).thenReturn(owner);

        CreateRestaurantCommand createRestaurantCommand = new CreateRestaurantCommand(restaurant);
        Restaurant restaurantCreated = createRestaurantUseCase.execute(createRestaurantCommand);

        assertEquals(restaurant.getId(), restaurantCreated.getId());
        assertEquals(restaurant.getName(), restaurantCreated.getName());

    }
}
