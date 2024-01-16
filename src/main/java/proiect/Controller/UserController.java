package proiect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.Exceptions.Unauthorised;
import proiect.Micunelte.*;
import proiect.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody final User user) {
        if(user.getRole().equals(Role.DELIVERER)){
            throw new Unauthorised("A deliverer cannot create account by themselves");
        }
        else{
            final var savedUser = service.createUser(user);
            return ResponseEntity.ok(savedUser);
        }
    }

    @PostMapping("/createDeliverer/{adminUsername}")
    public ResponseEntity<User> createDeliverer(@PathVariable String adminUsername, @RequestBody final User user) {
        final boolean isAdmnistrator = service.checkUserAdmnistrator(adminUsername);

        if(isAdmnistrator){
            if(user.getRole().equals(Role.DELIVERER)){
                final var savedUser = service.createUser(user);
                return ResponseEntity.ok(savedUser);
            }
            else {
                throw new Unauthorised("Unauthorised to create anything but a deliverer");
            }
        }
        else{
           throw new Unauthorised("Unauthorised to create deliverer");
        }
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
        else{
            throw new Unauthorised("Not allowed to update location");
        }
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username, @RequestBody AdminUsername adminUsername) {


        final boolean isUserAdministrator = service.checkUserAdmnistrator(adminUsername.getAdminUsername());

        if (isUserAdministrator) {
            service.deleteUser(username);
        }
        else{
            throw new Unauthorised("Not allowed to delete user");
        }
    }
}
