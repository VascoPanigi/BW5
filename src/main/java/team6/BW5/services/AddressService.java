package team6.BW5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Address;
import team6.BW5.entities.Municipality;
import team6.BW5.exceptions.NotFoundException;
import team6.BW5.payloads.AddressDTO;
import team6.BW5.repositories.AddressRepository;

import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MunicipalityService municipalityService;

    public Address findById(UUID addressId) {
        return this.addressRepository.findById(addressId).orElseThrow(() -> new NotFoundException(addressId));
    }

    public Address saveAddress(AddressDTO addressPayload) {

        Municipality municipality = municipalityService.findById(addressPayload.municipality_id());
        Address address = new Address(addressPayload.street(), addressPayload.zipCode(), municipality);
        return this.addressRepository.save(address);
    }
}
