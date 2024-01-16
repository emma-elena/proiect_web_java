package proiect.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proiect.Entity.UserEntity;
import proiect.Micunelte.Role;
import proiect.Micunelte.User;
import proiect.Repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private User user;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("petrica");
        user.setUsername("username1");
        user.setEmail("mail@example.com");
        user.setPassword("Parola");
        user.setPhoneNumber("0737057469");
        user.setDeliveryAddress("Adresa10");
        user.setRole(Role.CUSTOMER);


        userEntity = new UserEntity();
        userEntity.setName("petrica");
        userEntity.setUsername("username1");
        userEntity.setEmail("mail@example.com");
        userEntity.setPassword("Parola");
        user.setPhoneNumber("0737057469");
        user.setDeliveryAddress("Adresa10");
        userEntity.setRole(Role.CUSTOMER);
    }

    @Test
    void createUserTest() {
        when(repository.save(any(UserEntity.class))).thenReturn(userEntity);
        User response = service.createUser(user);

        assertNotNull(response);
        assertEquals(user.getUsername(), response.getUsername());
        verify(repository, times(1)).save(any(UserEntity.class));
    }
}