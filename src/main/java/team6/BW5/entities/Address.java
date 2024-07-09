package team6.BW5.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Municipality municipality_id;
    private Province province;


    public Address(String street, String zipCode, Municipality municipality_id, Province province) {
        this.street = street;
        this.zipCode = zipCode;
        this.municipality_id = municipality_id;
        this.province = province;
    }
}
