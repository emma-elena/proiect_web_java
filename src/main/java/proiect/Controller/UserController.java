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

    @PostMapping() //PostMapping este tipul de request cu path in () unde este cazul; pt a face diferenta intre mai multe tipuri de request de acelasi fel, pun in () path diferit in functie de necesitati
    public ResponseEntity<User> create(@RequestBody final User user) {
        if(user.getRole().equals(Role.DELIVERER)){
            throw new Unauthorised("A deliverer cannot create account by themselves");
        }
        else{
            final var savedUser = service.createUser(user);
            return ResponseEntity.ok(savedUser);
        }
    }

    @PostMapping("/createDeliverer/{adminUsername}")//path variable intre {} pe care il declar imediat dupa; mai intai pun path variable si dupa orice altceva
    public ResponseEntity<User> createDeliverer(@PathVariable String adminUsername, @RequestBody final User user) {
        final boolean isAdmnistrator = service.checkUserAdministrator(adminUsername);

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

    //Get si delete nu pot avea RequestBody
    @GetMapping("/{username}")
    public User getUsername(@PathVariable String username) {
        return service.getUser(username);
    }

    @PutMapping()
    public ResponseEntity<User> updateLocation(@RequestBody final UpdateLocation updateLocation)
    {
        //verifica daca persoana care vrea sa faca request este autorizata sau nu
        final boolean isUserCustomer = service.checkUserCustomer(updateLocation.getUsername());

        if (isUserCustomer) {
            final var updatedUser = service.updateLocation(updateLocation);
            return ResponseEntity.ok(updatedUser);
        }
        else{
            throw new Unauthorised("Not allowed to update location");
        }
    }

    @PutMapping("/updateDelivererInfo/{adminUsername}")
    public ResponseEntity<User> updateDelivererInfo(@PathVariable String adminUsername, @RequestBody final UpdateDelivererInfo updateDelivererInfo)
    {
        final boolean isUserAdministrator = service.checkUserAdministrator(adminUsername);

        if (isUserAdministrator) {
            final boolean isUserDeliverer= service.checkUserDeliverer(updateDelivererInfo.getDelivererUsername());

            if (isUserDeliverer){
                final var updatedUser = service.updateCarRegistrationNumber(updateDelivererInfo);
                return ResponseEntity.ok(updatedUser);
            }
            else {
                throw new Unauthorised("Admin can only update deliverer");
            }
        }
        else{
            throw new Unauthorised("Not allowed to update deliverer info");
        }
    }

    @DeleteMapping("/{username}/{adminUsername}")
    public void deleteUser(@PathVariable String username, @PathVariable String adminUsername) {
        final boolean isUserAdministrator = service.checkUserAdministrator(adminUsername);

        if (isUserAdministrator) {
            service.deleteUser(username);
        }
        else{
            throw new Unauthorised("Not allowed to delete user");
        }
    }
}
