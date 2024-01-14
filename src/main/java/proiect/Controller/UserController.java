package proiect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.Micunelte.AdminUsername;
import proiect.Micunelte.Order;
import proiect.Micunelte.UpdateLocation;
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

    @GetMapping("/{username}")
    public User getUsername(@PathVariable String username) {
        return service.getUser(username);
    }

    @PutMapping()
    public ResponseEntity<User> updateLocation(@RequestBody final UpdateLocation updateLocation)
    {
        final boolean isUserCustomer = service.checkUserCustomer(updateLocation.getUsername());

        if (isUserCustomer) {
            final var updatedUser = service.updateLocation(updateLocation);
            return ResponseEntity.ok(updatedUser);
        }
        return (ResponseEntity<User>) ResponseEntity.status(401);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username, @RequestBody AdminUsername adminUsername) {


        final boolean isUserAdministrator = service.checkUserAdmnistrator(adminUsername.getAdminUsername());

        if (isUserAdministrator) {
            service.deleteUser(username);
        }
    }
}
