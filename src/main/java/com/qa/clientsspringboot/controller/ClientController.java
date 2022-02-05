package com.qa.clientsspringboot.controller;

import com.qa.clientsspringboot.domain.Client;
import com.qa.clientsspringboot.service.ClientServiceInterface;
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

    @PostMapping
    public Client createClient(@RequestBody Client client){
        return this.clientService.createClient(client);

    }

    @GetMapping
    public List<Client> getAllClients(){
        return this.clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id){
        return this.clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client){
        return this.clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public Client deleteClient(@PathVariable Long id){
        return this.clientService.deleteClient(id);
    }
}
