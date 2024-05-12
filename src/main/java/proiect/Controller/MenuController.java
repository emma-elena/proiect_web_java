package proiect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proiect.Entity.MenuEntity;
import proiect.Service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService service;

    @PostMapping()
    public ResponseEntity<MenuEntity> create(@RequestBody final MenuEntity menu) {
        final var saved= service.create(menu);
        return ResponseEntity.ok(saved);
    }
}
