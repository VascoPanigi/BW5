package team6.BW5.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team6.BW5.entities.Client;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.payloads.ClientDTO;
import team6.BW5.services.ClientService;

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
    

}
