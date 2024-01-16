package proiect.Micunelte;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
//nu ma folosesc in cod de chestiile din entity, sa fac chestii cu BD, asa ca imi creez separat
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String deliveryAddress;
    private String carRegistrationNumber;

    private Role role;
}
