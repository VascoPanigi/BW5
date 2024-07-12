package team6.BW5.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
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
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String street;
    private String zipCode;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;


    public Address(String street, String zipCode, Municipality municipality_id) {
        this.street = street;
        this.zipCode = zipCode;
        this.municipality = municipality_id;
    }
}
