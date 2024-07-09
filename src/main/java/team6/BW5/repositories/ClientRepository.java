package team6.BW5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team6.BW5.entities.Client;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
