package com.qa.clientsspringboot.service;

import com.qa.clientsspringboot.domain.Client;
import com.qa.clientsspringboot.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientService implements ClientServiceInterface{

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        return null;
    }

    @Override
    public Client getClientById(Long id) {
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        return null;
    }

    @Override
    public List<Client> getClientsByName() {
        return null;
    }

    @Override
    public Client updateClient(Long id, Client client) {
        return null;
    }

    @Override
    public Client deleteClient(Long id) {
        return null;
    }
}
