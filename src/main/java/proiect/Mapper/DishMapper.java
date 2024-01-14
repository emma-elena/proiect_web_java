package proiect.Mapper;

import org.springframework.stereotype.Component;
import proiect.Entity.DishEntity;
import proiect.Micunelte.Dish;

@Component
public class DishMapper {
    public Dish toResponse(DishEntity dish) {
        Dish response = new Dish();

        response.setDishName(dish.getDishName());

        return response;
    }

    public DishEntity toEntity(Dish dish) {
        DishEntity dishEntity = new DishEntity();

        dishEntity.setDishName(dish.getDishName());

        return dishEntity;
    }
}
