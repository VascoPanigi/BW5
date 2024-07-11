package team6.BW5.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team6.BW5.entities.Client;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.payloads.ClientDTO;
import team6.BW5.services.ClientService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public Page<Client> getAllClients(@RequestParam(defaultValue = "0") int pageNum,
                                   @RequestParam(defaultValue = "10") int pageSize,
                                   @RequestParam(defaultValue = "id") String sortBy) {

        return this.clientService.getAllClients(pageNum, pageSize, sortBy);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin')")
    public Client saveClient(@RequestBody @Validated ClientDTO clientPayload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return clientService.saveClient(clientPayload);
    }

//In un secondo momento permettere ai client di modificare i propri dati
    @PostMapping("/{autoreId}/companyLogo")
    @PreAuthorize("hasAuthority('admin')")
    public String uploadAvatar(@RequestParam("companyLogo") MultipartFile image, @PathVariable UUID autoreId) throws IOException {
        return this.clientService.uploadImage(autoreId, image);
    }


    @GetMapping("/query")
    public Page<Client> getClientsByProvince(@RequestParam(defaultValue = "0") int pageNum,
                                   @RequestParam(defaultValue = "10") int pageSize,
                                   @RequestParam(defaultValue = "id") String sortBy) {

        return this.clientService.orderClientsByProvince(pageNum, pageSize, sortBy);
    }


}
