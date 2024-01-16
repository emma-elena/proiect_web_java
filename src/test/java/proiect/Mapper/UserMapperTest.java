package proiect.Mapper;

import org.junit.jupiter.api.Test;
import proiect.Micunelte.Role;
import proiect.Micunelte.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTest {

    private UserMapper userMapper = new UserMapper();

    @Test
    void toEntityTest() {
        final var customer = new User("numele", "username2", "email@email.com", "Parola1234", "0722222223", "adresa2", "B999BBC", Role.CUSTOMER);


        final var userEntity = userMapper.toEntity(customer);

        assertNotNull(userEntity);
        assertNotNull(userEntity.getEmail());
        assertEquals("username2", userEntity.getUsername());
        assertNotNull(userEntity.getPassword());
        assertEquals(Role.CUSTOMER, userEntity.getRole());
    }
}
