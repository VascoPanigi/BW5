package team6.BW5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    private String effectiveRole;

    @ManyToMany(mappedBy = "rolesList")
    private List<User> userList;

    public Role(String effectiveRole) {
        this.effectiveRole = effectiveRole;
    }
}
