package team6.BW5.payloads.invoices;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record NewInvoiceResponseDTO(
        @NotEmpty
        UUID id) {
}
