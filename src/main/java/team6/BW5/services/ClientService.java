package team6.BW5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Client;
import team6.BW5.enums.ClientType;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.repositories.ClientRepository;

import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public static ClientType convertInvoiceStatusToStr(String clientType) {
        try {
            return ClientType.valueOf(clientType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid client type: " + clientType + ". Choose between PA, SAS, SPA, SRL. Exception " + e);
        }
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientById(UUID id) {
        return clientRepository.findById(id).get();
    }

    public void deleteClientById(UUID id) {
        clientRepository.deleteById(id);
    }


}
