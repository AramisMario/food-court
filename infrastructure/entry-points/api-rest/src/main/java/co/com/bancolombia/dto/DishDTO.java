package co.com.bancolombia.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class DishDTO {

    @Size(min = 1, max = 60)
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z0-9 ]+$")
    private String name;
    @Positive
    private Integer price;
    @Size(min = 0, max = 500)
    private String description;
    @NotBlank
    private String urlImage;
    @NotBlank
    private String category;
    private Boolean active;
    @Positive
    private Integer restaurantId;
    @Positive
    private Integer ownerId;
}
