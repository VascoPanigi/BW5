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
import team6.BW5.payloads.invoices.NewInvoiceDTO;
import team6.BW5.repositories.InvoiceRepository;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;



    public Invoice save(NewInvoiceDTO body) {
        Invoice newInvoice = new Invoice(body.date(), body.amount(), convertInvoiceStatusToStr(body.status()));
        return invoiceRepository.save(newInvoice);
    }

    public static InvoiceStatus convertInvoiceStatusToStr(String deviceStatus){
        try {
            return InvoiceStatus.valueOf(deviceStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid invoice status: " + deviceStatus + ". Choose between PENDING, APPROVED, SENT, PAID, CANCELLED. Exception " + e);
        }
    }
}
