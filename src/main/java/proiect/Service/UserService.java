package proiect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.Entity.UserEntity;
import proiect.Exceptions.UserNotFound;
import proiect.Mapper.UserMapper;
import proiect.Micunelte.Role;
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
        return user.getRole().equals(Role.CUSTOMER);
    }

    public User getUser(String username) {
        UserEntity user = repository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found for username " + username));
        return mapper.toResponse(user);
    }
}
