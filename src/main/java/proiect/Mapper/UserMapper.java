package proiect.Mapper;

import org.springframework.stereotype.Component;
import proiect.Entity.UserEntity;
import proiect.Micunelte.Role;
import proiect.Micunelte.User;

@Component
public class UserMapper {
    public User toResponse(UserEntity user) {
        User response = new User();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setPhoneNumber(user.getPhoneNumber());

        response.setRole(user.getRole());
        if (user.getRole().equals(Role.CUSTOMER))
            response.setDeliveryAddress(user.getDeliveryAddress());
        if (user.getRole().equals(Role.DELIVERER))
            response.setCarRegistrationNumber(user.getCarRegistrationNumber());
        return response;
    }

    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setName(user.getName());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setPhoneNumber(user.getPhoneNumber());

        userEntity.setRole(user.getRole());
        if (user.getRole().equals(Role.CUSTOMER))
            userEntity.setDeliveryAddress(user.getDeliveryAddress());
        if (user.getRole().equals(Role.DELIVERER))
            userEntity.setCarRegistrationNumber(user.getCarRegistrationNumber());

        return userEntity;
    }
}

