package proiect.Micunelte;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dish {
    private UUID id;

    private String dishName;
    private String description;
    private Double price;
    private Double calories;
}
