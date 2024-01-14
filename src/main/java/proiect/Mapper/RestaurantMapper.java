package proiect.Mapper;

import org.springframework.stereotype.Component;
import proiect.Entity.RestaurantEntity;
import proiect.Micunelte.Restaurant;

@Component
public class RestaurantMapper {
    public Restaurant toResponse(RestaurantEntity restaurant) {
        Restaurant response = new Restaurant();

        response.setRestaurantName(restaurant.getRestaurantName());

        return response;
    }

    public RestaurantEntity toEntity(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();

        restaurantEntity.setRestaurantName(restaurant.getRestaurantName());

        return restaurantEntity;
    }
}
