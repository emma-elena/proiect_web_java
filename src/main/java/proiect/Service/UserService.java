package proiect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.Entity.UserEntity;
import proiect.Exceptions.UserNotFound;
import proiect.Mapper.UserMapper;
import proiect.Micunelte.Role;
import proiect.Micunelte.UpdateDelivererInfo;
import proiect.Micunelte.UpdateLocation;
import proiect.Micunelte.User;
import proiect.Repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public User createUser(User user){
        repository.save(mapper.toEntity(user));   //Adaugare user in baza de date
        Optional<UserEntity> userEntity= repository.findByUsername(user.getUsername());
        return mapper.toResponse(userEntity.get());
    }

    public boolean checkUserCustomer(String username) {
        UserEntity user = repository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found for username " + username));
        return user.getRole()==(Role.CUSTOMER);
    }

    public boolean checkUserDeliverer(String deliverer) {
        UserEntity user = repository.findByUsername(deliverer).orElseThrow(() -> new UserNotFound("User not found for username " + deliverer));
        return user.getRole()==(Role.DELIVERER);
    }

    public boolean checkUserAdministrator(String username) {
        UserEntity user = repository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found for username " + username));
        return user.getRole()==(Role.ADMINISTRATOR);
    }

    public User getUser(String username) {
        UserEntity user = repository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found for username " + username));
        return mapper.toResponse(user);
    }

    public User updateLocation(UpdateLocation updateLocation) {
        UserEntity user = repository.findByUsername(updateLocation.getUsername()).orElseThrow(() -> new UserNotFound("User not found for username " + updateLocation.getUsername()));
        user.setDeliveryAddress(updateLocation.getLocation());
        repository.save(user);
        return mapper.toResponse(user);
    }

    public User updateCarRegistrationNumber(UpdateDelivererInfo updateDelivererInfo) {
        UserEntity user = repository.findByUsername(updateDelivererInfo.getDelivererUsername()).orElseThrow(() -> new UserNotFound("User not found for username " + updateDelivererInfo.getDelivererUsername()));
        user.setCarRegistrationNumber(updateDelivererInfo.getCarRegistrationNumber());
        repository.save(user);
        return mapper.toResponse(user);
    }

    public void deleteUser(String username) {
        UserEntity user = repository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found for username " + username));
        repository.delete(user);
    }
}
