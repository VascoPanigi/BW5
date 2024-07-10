package team6.BW5.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team6.BW5.entities.Client;

import java.util.UUID;


@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Query("SELECT c FROM Client c " +
            "JOIN c.headOffice a " +
            "JOIN a.municipality m " +
            "JOIN m.province p " +
            "ORDER BY p.name")
    Page<Client> orderClientsByProvince(Pageable pageable);
}
