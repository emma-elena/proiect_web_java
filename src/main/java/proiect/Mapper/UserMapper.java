package proiect.Mapper;

import org.springframework.stereotype.Component;
import proiect.Entity.UserEntity;
import proiect.Micunelte.Role;
import proiect.Micunelte.User;

@Component
public class UserMapper {
    public User toResponse(UserEntity user) {
        User response = new User();

        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());

        response.setRole(user.getRole());
        if (user.getRole() == Role.CUSTOMER)
            response.setDeliveryAddress(user.getDeliveryAddress());
        return response;
    }

    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        userEntity.setRole(user.getRole());
        if (user.getRole() == Role.CUSTOMER)
            userEntity.setDeliveryAddress(user.getDeliveryAddress());

        return userEntity;
    }
}

