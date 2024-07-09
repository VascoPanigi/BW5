package team6.BW5.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue
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
