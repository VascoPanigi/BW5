package team6.BW5.payloads.RoleDTO;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record RoleAssignedDTO(
        @NotEmpty
        UUID id
) {
}
