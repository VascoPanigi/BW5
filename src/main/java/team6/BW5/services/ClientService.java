package team6.BW5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team6.BW5.entities.Address;
import team6.BW5.entities.Client;
import team6.BW5.enums.ClientType;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.exceptions.NotFoundException;
import team6.BW5.payloads.ClientDTO;
import team6.BW5.repositories.ClientRepository;

import java.io.IOException;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    private Cloudinary cloudinary;

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

    public String uploadImage(UUID id, MultipartFile file) throws IOException {
        Client found = this.clientRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        String img = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setCompanyLogo(img);
        this.clientRepository.save(found);
        return img;

    }

    public Page<Client> orderClientsByProvince(int pageNum, int pageSize, String sortBy) {
        if (pageSize > 500) pageSize = 500;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        return clientRepository.orderClientsByProvince(pageable);
    }


}
