package team6.BW5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    private String email;

    private String name;

    private String surname;

    private String avatarURL;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> rolesList;

    public User(String username, String email, String name, String surname, String avatarURL, List<Role> rolesList) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.avatarURL = avatarURL;
        this.rolesList = rolesList;
    }
}


