package proiect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.Entity.UserEntity;
import proiect.Mapper.UserMapper;
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
        repository.save(mapper.toEntity(user));
        Optional<UserEntity> userEntity= repository.findByUsername(user.getUsername());
        return mapper.toResponse(userEntity.get());

    }
}
