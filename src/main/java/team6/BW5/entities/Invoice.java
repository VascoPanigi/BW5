package team6.BW5.entities;

import jakarta.persistence.*;
import lombok.*;
import team6.BW5.enums.InvoiceStatus;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
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
    private InvoiceStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Invoice(LocalDate date, double amount, InvoiceStatus status) {
        this.date = date;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "date=" + date +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", client=" + client +
                '}';
    }
}