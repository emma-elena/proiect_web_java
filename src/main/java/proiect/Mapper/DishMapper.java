package proiect.Mapper;

import org.springframework.stereotype.Component;
import proiect.Entity.DishEntity;
import proiect.Micunelte.Dish;

@Component
public class DishMapper {
    public Dish toResponse(DishEntity dish) {
        Dish response = new Dish();

        response.setDishName(dish.getDishName());
        response.setDescription(dish.getDescription());
        response.setPrice(dish.getPrice());
        response.setCalories(dish.getCalories());

        return response;
    }

    public DishEntity toEntity(Dish dish) {
        DishEntity dishEntity = new DishEntity();

        dishEntity.setDishName(dish.getDishName());
        dishEntity.setDescription(dish.getDescription());
        dishEntity.setPrice(dish.getPrice());
        dishEntity.setCalories(dish.getCalories());

        return dishEntity;
    }
}
