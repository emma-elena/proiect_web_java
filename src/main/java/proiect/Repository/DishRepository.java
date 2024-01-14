package proiect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.Entity.DishEntity;
import proiect.Entity.RestaurantEntity;
import proiect.Micunelte.Dish;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, UUID> {
    Optional<DishEntity> findByDishName(String dishName);
}
