package proiect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.Entity.DishEntity;
import proiect.Mapper.DishMapper;
import proiect.Micunelte.Dish;
import proiect.Repository.DishRepository;

import java.util.Optional;

@Service
public class DishService {
    @Autowired
    private DishRepository repository;

    @Autowired
    private DishMapper mapper;

    public Dish createDish(Dish dish){
        repository.save(mapper.toEntity(dish));   //Adaugare user in baza de date
        Optional<DishEntity> dishEntity= repository.findByDishName(dish.getDishName());
        return mapper.toResponse(dishEntity.get());
    }
}
