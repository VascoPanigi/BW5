package team6.BW5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team6.BW5.entities.Invoice;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.payloads.invoices.NewInvoiceDTO;
import team6.BW5.payloads.invoices.NewInvoiceResponseDTO;
import team6.BW5.services.InvoiceService;

import java.util.UUID;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{clientId}")
    @PreAuthorize("hasAuthority('admin')")
    public Page<Invoice> getInvoicesByClient(@PathVariable UUID clientId,
                                             @RequestParam(defaultValue = "0") int pageNum,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        return this.invoiceService.getInvoicesByClient(clientId, pageNum, pageSize, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin')")
    public NewInvoiceResponseDTO saveInvoice(@RequestBody @Validated NewInvoiceDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new NewInvoiceResponseDTO(this.invoiceService.saveInvoice(body).getId());
    }
}
