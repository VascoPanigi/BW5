package team6.BW5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Address;
import team6.BW5.entities.Municipality;
import team6.BW5.exceptions.NotFoundException;
import team6.BW5.repositories.AddressRepository;

import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address findById(UUID addressId) {
        return this.addressRepository.findById(addressId).orElseThrow(() -> new NotFoundException(addressId));
    }
}
