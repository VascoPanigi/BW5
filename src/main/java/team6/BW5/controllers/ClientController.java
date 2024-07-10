package team6.BW5.controllers;


import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Client saveClient(@RequestBody @Validated ClientDTO clientPayload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return clientService.saveClient(clientPayload);
    }


    @PostMapping("/{autoreId}/companyLogo")
    public String uploadAvatar(@RequestParam("companyLogo") MultipartFile image, @PathVariable UUID autoreId) throws IOException {
        return this.clientService.uploadImage(autoreId, image);
    }


}
