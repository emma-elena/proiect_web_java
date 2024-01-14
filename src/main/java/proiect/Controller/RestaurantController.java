package proiect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proiect.Micunelte.Restaurant;
import proiect.Micunelte.User;
import proiect.Service.RestaurantService;
import proiect.Service.UserService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    @PostMapping()
    public ResponseEntity<Restaurant> create(@RequestBody final Restaurant restaurant) {
        final var savedRestaurant= service.createRestaurant(restaurant);
        return ResponseEntity.ok(savedRestaurant);
    }
}