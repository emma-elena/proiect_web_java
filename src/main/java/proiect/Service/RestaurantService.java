package proiect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.Entity.RestaurantEntity;
import proiect.Entity.UserEntity;
import proiect.Mapper.RestaurantMapper;
import proiect.Micunelte.Restaurant;
import proiect.Repository.RestaurantRepository;

import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private RestaurantMapper mapper;

    public Restaurant createRestaurant(Restaurant restaurant){
        repository.save(mapper.toEntity(restaurant));   //Adaugare user in baza de date
        Optional<RestaurantEntity> restaurantEntity= repository.findByRestaurantName(restaurant.getRestaurantName());
        return mapper.toResponse(restaurantEntity.get());

    }
}
