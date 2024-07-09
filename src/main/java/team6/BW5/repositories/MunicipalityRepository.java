package team6.BW5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team6.BW5.entities.Municipality;

import java.util.UUID;

public interface MunicipalityRepository extends JpaRepository<Municipality, UUID> {
}
