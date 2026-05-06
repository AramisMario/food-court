package co.com.bancolombia.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.management.relation.Role;

import co.com.bancolombia.dto.RestaurantDTO;
import co.com.bancolombia.model.restaurant.Restaurant;
import co.com.bancolombia.usecase.createrestaurant.CreateRestaurantUseCase;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = TestConfig.class)
class ApiRestTest {

    @Autowired
    private WebApplicationContext context;

    private RestTestClient client;

    @MockitoBean
    private CreateRestaurantUseCase createRestaurantUseCase;

    @BeforeEach
    void setup() {
        client = RestTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void shouldReturnValueFromCreateUserUseCase() {

        Restaurant restaurant = Restaurant.builder()
                .id(3)
                .name("name")
                .taxIdentification("12345678")
                .addres("Calle 42#23-98 Local 123")
                .phone("3456237")
                .urlLogo("path del logo")
                .ownerId(57)
                .build();

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName("name");
        restaurantDTO.setAddres("Calle 42#23-98 Local 123");
        restaurantDTO.setTaxIdentification("123456789");
        restaurantDTO.setPhone("+573456789");
        restaurantDTO.setUrlLogo("path del logo");
        restaurantDTO.setOwnerId(57);

        when(createRestaurantUseCase.execute(any())).thenReturn(restaurant);

        client.post()
                .uri("/api/createrestaurant/path")
                .body(restaurantDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(new ParameterizedTypeReference<ApiResponseBody<Restaurant>>() {
                }).value(response -> {
                    System.out.println("EN LA RESPUESTA");
                    Restaurant data = response.getData();
                    assertEquals(data.getId(), restaurant.getId());
                    assertEquals(data.getName(), restaurant.getName());
                    assertEquals(data.getAddres(), restaurant.getAddres());
                });
    }
    
    @Test
    void shouldReturnNotFoundForInvalidPath() {
        client.get()
                .uri("/api/restaurant/invalid")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }
}
