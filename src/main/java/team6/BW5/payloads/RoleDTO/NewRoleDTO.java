package team6.BW5.payloads.RoleDTO;

import jakarta.validation.constraints.NotEmpty;

public record NewRoleDTO(
        @NotEmpty(message = "Effective Role field is required")
        String effectiveRole
) {
}
