package team6.BW5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record ClientDTO(
        @NotEmpty(message = "Company name cannot be empty")
        String companyName,
        @NotEmpty(message = "VAT number cannot be empty")
        String vatNumber,
        @NotEmpty(message = "Email cannot be empty")
        @Email
        String email,
        @NotEmpty(message = "Insertion date cannot be empty")
        Date insertionDate,
        @NotEmpty(message = "Last contact date cannot be empty")
        Date lastContactDate,
        @NotEmpty(message = "Annual turnover cannot be empty")
        int annualTurnover,
        @NotEmpty(message = "Pec cannot be empty")
        String pec,
        @NotEmpty(message = "Phone number cannot be empty")
        String phoneNumber,
        @NotEmpty(message = "Contact email cannot be empty")
        @Email
        String contactEmail,
        @NotEmpty(message = "Contact name cannot be empty")
        String contactName,
        @NotEmpty(message = "Contact surname cannot be empty")
        String contactSurname,
        @NotEmpty(message = "Contact phone number cannot be empty")
        String contactPhoneNumber
) {
}
