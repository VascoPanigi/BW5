package team6.BW5.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.BW5.entities.Address;

import java.util.UUID;


@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
