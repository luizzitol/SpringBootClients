package com.qa.clientsspringboot.service;

import com.qa.clientsspringboot.domain.Client;
import com.qa.clientsspringboot.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.SpringObjenesis;

import java.util.List;
import java.util.Optional;

public class ClientService implements ClientServiceInterface{

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> getClientsByName(String name) {
        return clientRepository.findClientsByName(name);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Optional<Client> toBeUpdated = clientRepository.findById(id);
        if (toBeUpdated.isPresent()){
            Client present = toBeUpdated.get();
            present.setDob(client.getDob());
            present.setName(client.getName());
            present.setEmail(client.getEmail());
            return clientRepository.save(present);
        }
        return null;
    }

    @Override
    public Client deleteClient(Long id) {
        Optional<Client> toBeDeleted = clientRepository.findById(id);
        if (toBeDeleted.isPresent()){
            clientRepository.deleteById(id);
            return toBeDeleted.get();
        }
        return null;
    }
}
