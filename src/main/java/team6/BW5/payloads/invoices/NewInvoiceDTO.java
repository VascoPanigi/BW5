package team6.BW5.payloads.invoices;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.UUID;

public record NewInvoiceDTO(@NotEmpty(message = "The date is a mandatory field.")
                            LocalDate date,
                            @NotEmpty(message = "The amount is a mandatory field.")
                            double amount,
                            @NotEmpty(message = "The status of the invoice is a mandatory field.")
                            String status,
                            @NotEmpty(message = "The client is a mandatory field.")
                            UUID clientId) {
}
