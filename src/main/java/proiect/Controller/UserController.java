package proiect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proiect.Micunelte.User;
import proiect.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody final User user) {
        final var savedUser = service.createUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
