package AutoService.DiplomaApplication.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "table_cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String brand;
    private String vin_number;
    private String address;
    private String phone;
    private String number;
    private String name;
    private String surname;
    private List<String> description = new ArrayList<>();


    public Cars(Long id, Long userId, String brand, String vin_number, String address, String phone, String number, String name, String surname, List<String> description) {
        this.id = id;
        this.userId = userId;
        this.brand = brand;
        this.vin_number = vin_number;
        this.address = address;
        this.phone = phone;
        this.number = number;
        this.name = name;
        this.surname = surname;
        this.description = description;
    }
}
