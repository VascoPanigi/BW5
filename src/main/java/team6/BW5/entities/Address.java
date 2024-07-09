package team6.BW5.entities;


<<<<<<< HEAD
import jakarta.persistence.*;
=======
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
>>>>>>> Develop
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue
<<<<<<< HEAD
=======
    @Setter(AccessLevel.NONE)
>>>>>>> Develop
    private UUID id;
    private String street;
    private String zipCode;
    @ManyToOne
    @JoinColumn(name = "municipality")
    private Municipality municipality_id;


    public Address(String street, String zipCode, Municipality municipality_id) {
        this.street = street;
        this.zipCode = zipCode;
        this.municipality_id = municipality_id;
    }
}
