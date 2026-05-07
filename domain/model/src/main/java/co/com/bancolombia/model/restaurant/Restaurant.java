package co.com.bancolombia.model.restaurant;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Restaurant {

    private Integer id;
    private String name;
    private String taxIdentification;
    private String addres;
    private String phone;
    private String urlLogo;
    private Integer ownerId;
}
