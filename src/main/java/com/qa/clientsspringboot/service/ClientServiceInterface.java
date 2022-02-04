package com.qa.clientsspringboot.service;

import com.qa.clientsspringboot.domain.Client;

import java.util.List;

public interface ClientServiceInterface {
    //Create
    Client createClient(Client client);

    //Read
    Client getClientById(Long id);
    List<Client> getAllClients();
    List<Client> getClientsByName();

    //Update
    Client updateClient(Long id, Client client);

    //Delete
    Client deleteClient(Long id);
}
