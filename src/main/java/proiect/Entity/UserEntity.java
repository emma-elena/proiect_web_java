package proiect.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proiect.Micunelte.Role;

import java.util.List;
import java.util.UUID;

@Data //imi permite sa folosesc .getUserId fara sa mai scriu eu functiile in spate, este deja functia oferita de aceasta adnotare
@NoArgsConstructor
@AllArgsConstructor
@Entity //baza de date are nevoie de entitati ca sa faca o baza de date
@Table(name="USERS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; //uuid ca sa fie id-ul unic

    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String deliveryAddress;
    private String carRegistrationNumber;

    private Role role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders", nullable = true) //un user poate sa aiba mai multe orders
    private List<OrderEntity> orders;
}