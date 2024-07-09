package team6.BW5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Invoice;
import team6.BW5.payloads.invoices.NewInvoiceDTO;
import team6.BW5.repositories.InvoiceRepository;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Page<Invoice> getAllInvoices(int pageNum, int pageSize, String sortBy){
        if(pageSize>500) pageSize = 500;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        return invoiceRepository.findAll(pageable);
    }



}
