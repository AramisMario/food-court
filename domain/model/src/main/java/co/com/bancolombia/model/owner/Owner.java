package co.com.bancolombia.model.owner;

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
public class Owner {

    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String birthDate;
    private String phone;
    private String identificationDocument;
    
}
