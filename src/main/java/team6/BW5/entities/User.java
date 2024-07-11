package team6.BW5.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"password", "enabled", "authorities", "accountNonLocked", "credentialsNonExpired", "accountNonExpired"})
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String avatarURL;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> rolesList;

    public User(String username, String email, String password, String name, String surname, String avatarURL, Set<Role> rolesList) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.avatarURL = avatarURL;
        this.rolesList = rolesList;

    }

    public User(String username, String email, String password, String name, String surname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

}


