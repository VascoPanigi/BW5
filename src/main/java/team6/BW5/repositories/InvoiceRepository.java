package team6.BW5.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import team6.BW5.entities.Invoice;

import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID>, JpaSpecificationExecutor<Invoice> {
    Page<Invoice> findByClientId(UUID clientId, Pageable pageable);


    //Page<Invoice> findByClientName(String clientName, Pageable pageable);

    //Page<Invoice> findByYear(int year, Pageable pageable);

}
