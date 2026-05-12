package co.com.bancolombia.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import co.com.bancolombia.dto.DishDTO;
import lombok.RequiredArgsConstructor;
import co.com.bancolombia.model.dish.Dish;
import org.springframework.http.MediaType;
import org.osgi.annotation.bundle.Header;
import org.springframework.http.HttpStatus;
import co.com.bancolombia.dto.RestaurantDTO;
import org.springframework.http.ResponseEntity;
import co.com.bancolombia.model.restaurant.Restaurant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.bancolombia.usecase.createdish.CreateDishUseCase;
import co.com.bancolombia.usecase.createdish.CreateDishCommand;
import co.com.bancolombia.usecase.createdish.CreateDishUseCase;
import co.com.bancolombia.usecase.createrestaurant.CreateRestaurantCommand;
import co.com.bancolombia.usecase.createrestaurant.CreateRestaurantUseCase;
import co.com.bancolombia.usecase.updateDish.UpdateDishCommand;
import co.com.bancolombia.usecase.updateDish.UpdateDishUseCase;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import co.com.bancolombia.api.helpers.Headers;
import co.com.bancolombia.api.services.JwtService;

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
    private final CreateDishUseCase createDishUseCase;
    private final UpdateDishUseCase updateDishUseCase;
    private final JwtService jwtService;

    @PostMapping(path = "/createrestaurant/path")
    public ResponseEntity<ApiResponseBody<Restaurant>> createRestauran(
            @Valid @RequestBody RestaurantDTO restaurantDTO, HttpServletRequest request) {

        ApiResponse<Restaurant> apiResponse = new ApiResponse<>();
        String authHeader = request.getHeader("Authorization");
        Headers headers = Headers.getInstance();
        headers.setToken(authHeader);
        String autenticatedUserRoleName = jwtService.extractClaim(authHeader.substring(7), "role", String.class);
        try {
            Restaurant restaurant = Restaurant.builder()
                    .name(restaurantDTO.getName())
                    .taxIdentification(restaurantDTO.getTaxIdentification())
                    .addres(restaurantDTO.getAddres())
                    .phone(restaurantDTO.getPhone())
                    .urlLogo(restaurantDTO.getUrlLogo())
                    .ownerId(restaurantDTO.getOwnerId())
                    .build();

            CreateRestaurantCommand createRestaurantCommand = new CreateRestaurantCommand(restaurant,
                    autenticatedUserRoleName);

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
                            new ApiResponseBody<Restaurant>("INTERNAL_SERVER_ERROR", e.getMessage(),
                                    null));

                    break;
            }

        }

        return apiResponse.response();
    }

    @PostMapping(path = "/createdish/path")
    public ResponseEntity<ApiResponseBody<Dish>> createRestauran(@Valid @RequestBody DishDTO dishDTO,
            HttpServletRequest request) {
        ApiResponse<Dish> apiResponse = new ApiResponse<>();

        String authHeader = request.getHeader("Authorization");
        Headers headers = Headers.getInstance();
        headers.setToken(authHeader);
        String autenticatedUserRoleName = jwtService.extractClaim(authHeader.substring(7), "role", String.class);

        try {

            Dish dish = Dish.builder()
                    .name(dishDTO.getName())
                    .price(dishDTO.getPrice())
                    .category(dishDTO.getCategory())
                    .description(dishDTO.getDescription())
                    .urlImage(dishDTO.getUrlImage())
                    .build();

            CreateDishCommand createDishCommand = CreateDishCommand.builder()
                    .dish(dish)
                    .restaurantId(dishDTO.getRestaurantId())
                    .ownerId(dishDTO.getOwnerId())
                    .userAuthenticatedRole(autenticatedUserRoleName)
                    .build();

            Dish createdDish = createDishUseCase.execute(createDishCommand);

            apiResponse.setHttpStatus(HttpStatus.CREATED);
            apiResponse.setData(
                    new ApiResponseBody<Dish>("CREATED", "Plato creado", createdDish));

        } catch (Exception e) {

            switch (e.getMessage()) {

                case "USER_NOT_OWN_RESTAURANT":

                    apiResponse.setHttpStatus(HttpStatus.UNPROCESSABLE_CONTENT);
                    apiResponse.setData(new ApiResponseBody<Dish>("UNPROCESSABLE_CONTENT",
                            "El usuario no es propietario de ese restaurante", null));
                    break;

                default:

                    apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    apiResponse.setData(
                            new ApiResponseBody<Dish>("INTERNAL_SERVER_ERROR", "Error interno del servidor",
                                    null));

                    break;
            }
        }

        return apiResponse.response();

    }

    @PutMapping(path = "/updatedish/path/{id}")
    public ResponseEntity<ApiResponseBody<Dish>> updateDish(@PathVariable("id") Integer id,
            @RequestBody DishDTO dishDTO, HttpServletRequest request) {

        ApiResponse<Dish> apiResponse = new ApiResponse<>();

        String authHeader = request.getHeader("Authorization");
        Headers headers = Headers.getInstance();
        headers.setToken(authHeader);
        String token = authHeader.substring(7);
        String autenticatedUserRoleName = jwtService.extractClaim(token, "role", String.class);
        Integer ownerId = Integer.parseInt(jwtService.extractUserId(token));
        try {

            Dish dish = Dish.builder()
                    .name(dishDTO.getName())
                    .price(dishDTO.getPrice())
                    .category(dishDTO.getCategory())
                    .description(dishDTO.getDescription())
                    .urlImage(dishDTO.getUrlImage())
                    .active(dishDTO.getActive())
                    .build();

            UpdateDishCommand updateDishCommand = UpdateDishCommand.builder()
                    .dishId(id)
                    .dish(dish)
                    .userAuthenticatedRole(autenticatedUserRoleName)
                    .ownerId(ownerId)
                    .build();

            Dish updatedDish = updateDishUseCase.execute(updateDishCommand);

            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setData(
                    new ApiResponseBody<Dish>("OK", "Plato actualizado", updatedDish));
        } catch (Exception e) {
            switch (e.getMessage()) {

                default:

                    apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    apiResponse.setData(
                            new ApiResponseBody<Dish>("INTERNAL_SERVER_ERROR", e.getMessage(),
                                    null));
                    break;
            }
        }

        return apiResponse.response();
    }
}
