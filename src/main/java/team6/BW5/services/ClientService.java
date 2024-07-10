package team6.BW5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Address;
import team6.BW5.entities.Client;
import team6.BW5.enums.ClientType;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.payloads.ClientDTO;
import team6.BW5.repositories.ClientRepository;

import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AddressService addressService;

    public static ClientType convertClientTypeToStr(String clientType) {
        try {
            return ClientType.valueOf(clientType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid client type: " + clientType + ". Choose between PA, SAS, SPA, SRL. Exception " + e);
        }
    }

    public Client saveClient(ClientDTO clientPayload) {

        Address headOffice = addressService.findById(clientPayload.headOfficeId());
        Address headQuarters = addressService.findById(clientPayload.headQuartersId());

        Client client = new Client(clientPayload.companyName(), clientPayload.vatNumber(), clientPayload.email(),
                clientPayload.insertionDate(), clientPayload.lastContactDate(), clientPayload.annualTurnover(), clientPayload.pec(),
                clientPayload.phoneNumber(), clientPayload.contactEmail(), clientPayload.contactName(), clientPayload.contactSurname(),
                clientPayload.contactPhoneNumber(), convertClientTypeToStr(clientPayload.clientType()), clientPayload.companyLogo(),
                headOffice, headQuarters);
        return clientRepository.save(client);

    }

    public Client getClientById(UUID id) {
        return clientRepository.findById(id).get();
    }

    public void deleteClientById(UUID id) {
        clientRepository.deleteById(id);
    }


}
