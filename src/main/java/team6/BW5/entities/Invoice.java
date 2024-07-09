package team6.BW5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private LocalDate date;
    private double amount;
    private String status;

    //many to one con cliente

    public Invoice(LocalDate date, double amount, String status) {
        this.date = date;
        this.amount = amount;
        this.status = status;
    }
}