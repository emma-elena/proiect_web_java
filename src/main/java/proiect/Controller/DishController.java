package proiect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proiect.Micunelte.Dish;
import proiect.Service.DishService;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService service;

    @PostMapping()
    public ResponseEntity<Dish> create(@RequestBody final Dish dish) {
        final var savedDish= service.createDish(dish);
        return ResponseEntity.ok(savedDish);
    }
}
