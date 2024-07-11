package team6.BW5.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Invoice(LocalDate date, double amount, InvoiceStatus status) {
        this.date = date;
        this.amount = amount;
        this.status = status;
    }

    public Invoice(LocalDate date, double amount, InvoiceStatus status, Client client) {
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.client = client;
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