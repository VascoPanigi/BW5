package team6.BW5.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddressDTO(@NotEmpty(message = "Street name cannot be empty")
                         String street,
                         @NotEmpty(message = "Zip code cannot be empty")
                         String zipCode,
                         @NotNull
                         UUID municipality_id) {
}
