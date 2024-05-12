package proiect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.Entity.RatingEntity;
import proiect.Service.RatingService;

import java.util.UUID;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService service;

    @PostMapping()
    public ResponseEntity<RatingEntity> create(@RequestBody final RatingEntity rating) {
        final var saved= service.create(rating);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
