package co.com.bancolombia.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import co.com.bancolombia.dto.RestaurantDTO;
import org.springframework.http.ResponseEntity;
import co.com.bancolombia.model.restaurant.Restaurant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.bancolombia.usecase.createrestaurant.CreateRestaurantCommand;
import co.com.bancolombia.usecase.createrestaurant.CreateRestaurantUseCase;

/**
 * API Rest controller.
 * 
 * Example of how to declare and use a use case:
 * 
 * <pre>
 * private final MyUseCase useCase;
 * 
 * public String commandName() {
 *     return useCase.execute();
 * }
 * </pre>
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ApiRest {

    private final CreateRestaurantUseCase createRestaurantUsecase;

    @PostMapping(path = "/createrestaurant/path")
    public ResponseEntity<ApiResponseBody<Restaurant>> createRestauran(@Valid @RequestBody RestaurantDTO restaurantDTO) {

        ApiResponse<Restaurant> apiResponse = new ApiResponse<>();

        try {
            Restaurant restaurant = Restaurant.builder()
                    .name(restaurantDTO.getName())
                    .taxIdentification(restaurantDTO.getTaxIdentification())
                    .addres(restaurantDTO.getAddres())
                    .phone(restaurantDTO.getPhone())
                    .urlLogo(restaurantDTO.getUrlLogo())
                    .ownerId(restaurantDTO.getOwnerId())
                    .build();

            CreateRestaurantCommand createRestaurantCommand = new CreateRestaurantCommand(restaurant);

            Restaurant restaurantResponse = createRestaurantUsecase.execute(createRestaurantCommand);

            apiResponse.setHttpStatus(HttpStatus.CREATED);
            apiResponse.setData(
                    new ApiResponseBody<Restaurant>("CREATED", "El usurio es Owner", restaurantResponse));
        } catch (Exception e) {

            switch (e.getMessage()) {
                case "USER_NOT_OWNER":

                    apiResponse.setHttpStatus(HttpStatus.UNPROCESSABLE_CONTENT);
                    apiResponse.setData(
                            new ApiResponseBody<Restaurant>("UNPROCESSABLE_CONTENT", "El usuario no es Owner", null));

                    break;

                default:

                    apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    apiResponse.setData(
                            new ApiResponseBody<Restaurant>("INTERNAL_SERVER_ERROR", "Error interno del servidor", null));

                    break;
            }

        }

        return apiResponse.response();
    }

}
