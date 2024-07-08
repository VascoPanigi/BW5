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


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "provinces")
public class Province {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;


    
}
