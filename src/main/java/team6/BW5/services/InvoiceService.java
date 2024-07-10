package team6.BW5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Invoice;
import team6.BW5.enums.InvoiceStatus;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.exceptions.NotFoundException;
import team6.BW5.payloads.invoices.NewInvoiceDTO;
import team6.BW5.repositories.InvoiceRepository;

import java.util.UUID;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public static InvoiceStatus convertInvoiceStatusToStr(String deviceStatus) {
        try {
            return InvoiceStatus.valueOf(deviceStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid invoice status: " + deviceStatus + ". Choose between PENDING, APPROVED, SENT, PAID, CANCELLED. Exception " + e);
        }
    }

//    public Page<Invoice> getInvoicesByStatus(int pageNum, int pageSize, InvoiceStatus sortBy){
//        if(pageSize>500) pageSize = 500;
//        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(String.valueOf(sortBy)));
//        return invoiceRepository.findAll(pageable);
//    }

    public Page<Invoice> getInvoicesByClient(UUID clientId, int pageNum, int pageSize, String sortBy) {
        if (pageSize > 500) pageSize = 500;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        return invoiceRepository.findByClientId(clientId, pageable);
    }

    public Invoice save(NewInvoiceDTO body) {
        Invoice newInvoice = new Invoice(body.date(), body.amount(), convertInvoiceStatusToStr(body.status()));
        return invoiceRepository.save(newInvoice);
    }

    public Invoice findById(UUID id) {
        return this.invoiceRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Invoice uploadInvoiceStatus(UUID id, NewInvoiceDTO uploadedInvoice) {
        Invoice found = this.findById(id);
        found.setStatus(convertInvoiceStatusToStr(uploadedInvoice.status()));

        return this.invoiceRepository.save(found);
    }
}
