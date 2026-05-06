package co.com.bancolombia.jpa.restaurant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String taxIdentification;
    private String addres;
    private String phone;
    private String urlLogo;
    private int ownerId;
}
