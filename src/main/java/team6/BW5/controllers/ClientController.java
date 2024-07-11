package team6.BW5.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team6.BW5.entities.Client;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.payloads.ClientDTO;
import team6.BW5.services.ClientService;

import java.io.IOException;
import java.time.LocalDate;
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


    //    @GetMapping
//    public Page<Client> getClients(@RequestParam(defaultValue = "0") int pageNum,
//                                   @RequestParam(defaultValue = "10") int pageSize,
//                                   @RequestParam(defaultValue = "id") String sortBy) {
//
//        return this.clientService.orderClientsByProvince(pageNum, pageSize, sortBy);
//    }
    @GetMapping
    public Page<Client> queryDinamica(@RequestParam(defaultValue = "0") int pageNumber,
                                      @RequestParam(defaultValue = "10") int pageSize,
                                      @RequestParam(defaultValue = "id") String sortedBy,
                                      @RequestParam(required = false) Integer annualTurnover,
                                      @RequestParam(required = false) LocalDate insertionDate,
                                      @RequestParam(required = false) LocalDate lastContactDate,
                                      @RequestParam(required = false) String companyName) {
        return clientService.findClients(pageNumber, pageSize, sortedBy, annualTurnover, insertionDate, lastContactDate, companyName);

    }


}
