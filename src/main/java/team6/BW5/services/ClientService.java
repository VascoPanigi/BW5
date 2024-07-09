package team6.BW5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Client;
import team6.BW5.repositories.ClientRepository;

import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;


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
