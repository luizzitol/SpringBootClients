package com.qa.clientsspringboot.controller;

import com.qa.clientsspringboot.domain.Client;
import com.qa.clientsspringboot.exceptions.ClientNotFoundException;
import com.qa.clientsspringboot.exceptions.EmailTakenException;
import com.qa.clientsspringboot.exceptions.ErrorResponse;
import com.qa.clientsspringboot.service.ClientServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@CrossOrigin
public class ClientController {
    private ClientServiceInterface clientService;


    public ClientController(ClientServiceInterface clientService) {
        this.clientService = clientService;
    }

    //CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody Client client){
        return this.clientService.createClient(client);

    }

    //READ
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients(){
        return this.clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Client getClientById(@PathVariable Long id){
        return this.clientService.getClientById(id);
    }


    //UPDATE

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Client updateClient(@PathVariable Long id, @RequestBody Client client){
        return this.clientService.updateClient(id, client);
    }


    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Client deleteClient(@PathVariable Long id){
        return this.clientService.deleteClient(id);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoClientFoundException(ClientNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return  errorResponse;
    }

    @ExceptionHandler(EmailTakenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleNoClientFoundException(EmailTakenException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return  errorResponse;
    }

}

