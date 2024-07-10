package team6.BW5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team6.BW5.enums.ClientType;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue
    private UUID id;
    private String companyName;
    private String vatNumber;
    private String email;
    private LocalDate insertionDate;
    private LocalDate lastContactDate;
    private int annualTurnover;
    private String pec;
    private String phoneNumber;
    private String contactEmail;
    private String contactName;
    private String contactSurname;
    private String contactPhoneNumber;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;
    private String companyLogo;
    @OneToOne
    @JoinColumn(name = "headOffice_id")
    private Address headOffice;
    @OneToOne
    @JoinColumn(name = "headQuarters_id")
    private Address headQuarters;

    public Client(String companyName, String vatNumber, String email, LocalDate insertionDate, LocalDate lastContactDate, int annualTurnover, String pec, String phoneNumber, String contactEmail, String contactName, String contactSurname, String contactPhoneNumber, ClientType clientType, String companyLogo, Address headOffice, Address headQuarters) {
        this.companyName = companyName;
        this.vatNumber = vatNumber;
        this.email = email;
        this.insertionDate = insertionDate;
        this.lastContactDate = lastContactDate;
        this.annualTurnover = annualTurnover;
        this.pec = pec;
        this.phoneNumber = phoneNumber;
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactPhoneNumber = contactPhoneNumber;
        this.clientType = clientType;
        this.companyLogo = companyLogo;
        this.headOffice = headOffice;
        this.headQuarters = headQuarters;
    }
}
