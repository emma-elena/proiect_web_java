package proiect.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="MENU")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dishes", nullable = false)
    private List<DishEntity> dishes;
}
