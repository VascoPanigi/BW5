package team6.BW5.services;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Client;
import team6.BW5.entities.Invoice;
import team6.BW5.enums.InvoiceStatus;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.exceptions.NotFoundException;
import team6.BW5.payloads.invoices.NewInvoiceDTO;
import team6.BW5.repositories.InvoiceRepository;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientService clientService;

    public static InvoiceStatus convertInvoiceStatusToStr(String deviceStatus) {
        try {
            return InvoiceStatus.valueOf(deviceStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid invoice status: " + deviceStatus + ". Choose between PENDING, APPROVED, SENT, PAID, CANCELLED. Exception " + e);
        }
    }

    private static LocalDate convertYearStringToLocalDate(String year) {
        if (year == null || year.isEmpty()) {
            throw new IllegalArgumentException("L'anno non può essere null o vuoto");
        }
        int yearInt = Integer.parseInt(year);
        return LocalDate.of(yearInt, 1, 1); // Ritorna il primo giorno dell'anno specificato
    }


//    public Page<Invoice> getInvoicesByStatus(int pageNum, int pageSize, InvoiceStatus sortBy){
//        if(pageSize>500) pageSize = 500;
//        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(String.valueOf(sortBy)));
//        return invoiceRepository.findAll(pageable);
//    }

    public Invoice saveInvoice(NewInvoiceDTO body) {
        Client client = clientService.getClientById(body.clientId());
        Invoice newInvoice = new Invoice(body.date(), body.amount(), convertInvoiceStatusToStr(body.status()), client);
        return invoiceRepository.save(newInvoice);
    }

    public Page<Invoice> getInvoicesByClient(UUID clientId, int pageNum, int pageSize, String sortBy) {
        if (pageSize > 500) pageSize = 500;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        return invoiceRepository.findByClientId(clientId, pageable);
    }

    public Invoice findById(UUID id) {
        return this.invoiceRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Page<Invoice> findInvoices(int pageNumber,
                                      int pageSize,
                                      String sortedBy,
                                      String companyName,
                                      String status,
                                      LocalDate date,
                                      String year,
                                      String range1,
                                      String range2) {
        if (pageSize > 10) pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortedBy));
        return invoiceRepository.findAll(((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (companyName != null) {
                Join<Invoice, Client> clientJoin = root.join("client");
                predicates.add(criteriaBuilder.equal(clientJoin.get("companyName"), companyName));
            }
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), convertInvoiceStatusToStr(status)));
            }
            if (date != null) {
                predicates.add(criteriaBuilder.equal(root.get("date"), date));
            }
            if (year != null) {
                LocalDate startDate = convertYearStringToLocalDate(year);
                LocalDate finishDate = startDate.with(TemporalAdjusters.lastDayOfYear());
                predicates.add(criteriaBuilder.between(root.get("date"), startDate, finishDate));
            }
            if (range1 != null & range2 != null) {
                Double rangeStart = Double.parseDouble(range1);
                Double rangeEnd = Double.parseDouble(range2);
                predicates.add(criteriaBuilder.between(root.get("amount"), rangeStart, rangeEnd));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }), pageable);
    }

    public Invoice uploadInvoiceStatus(UUID id, NewInvoiceDTO uploadedInvoice) {
        Invoice found = this.findById(id);
        found.setStatus(convertInvoiceStatusToStr(uploadedInvoice.status()));

        return this.invoiceRepository.save(found);
    }


}
