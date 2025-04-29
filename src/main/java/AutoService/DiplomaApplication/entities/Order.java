package AutoService.DiplomaApplication.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "table_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;  // Описание ремонта
    private LocalDate date;  // Дата ремонта
    private String status;  // Статус ремонта (например, "В процессе", "Завершено")

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
