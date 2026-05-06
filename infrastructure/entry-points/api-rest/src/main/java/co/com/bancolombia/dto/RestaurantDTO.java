package co.com.bancolombia.dto;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class RestaurantDTO {
    @NotBlank
    @Size(min = 1, max = 60)
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z0-9 ]+$")
    private String name;
    @NotBlank
    @Pattern(regexp = "^[0-9]+$")
    private String taxIdentification;
    @NotBlank
    @Size(min = 1, max = 300)
    private String addres;
    @Pattern(regexp = "^(\\+\\d{1,2})?\\d{1,10}$")
    private String phone;
    @NotBlank
    private String urlLogo;
    @Positive
    private int ownerId;
}