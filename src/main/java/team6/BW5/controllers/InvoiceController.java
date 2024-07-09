package team6.BW5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team6.BW5.entities.Invoice;
import team6.BW5.enums.InvoiceStatus;
import team6.BW5.services.InvoiceService;

import java.util.UUID;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{clientId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Invoice> getInvoicesByClient( @PathVariable UUID clientId,
                                              @RequestParam(defaultValue = "0") int pageNum,
                                              @RequestParam(defaultValue = "10") int pageSize,
                                              @RequestParam(defaultValue = "id") String sortBy) {
        return this.invoiceService.getInvoicesByClient(clientId, pageNum, pageSize, sortBy);
    }
}
