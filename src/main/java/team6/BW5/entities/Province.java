package team6.BW5.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "provinces")
public class Province {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private String region;
    private String notation;

    public Province(String name, String region, String notation) {
        this.name = name;
        this.region = region;
        this.notation = notation;
    }
}
