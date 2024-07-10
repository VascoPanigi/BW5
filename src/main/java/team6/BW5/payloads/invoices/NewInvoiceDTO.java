package team6.BW5.payloads.invoices;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record NewInvoiceDTO(@NotNull(message = "The date is a mandatory field.")
                            LocalDate date,
                            @NotNull(message = "The amount is a mandatory field.")
                            double amount,
                            @NotEmpty(message = "The status of the invoice is a mandatory field.")
                            String status,
                            @NotNull(message = "The client is a mandatory field.")
                            UUID clientId) {
}
