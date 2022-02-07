package com.qa.clientsspringboot.service;

import com.qa.clientsspringboot.domain.Client;
import com.qa.clientsspringboot.exceptions.ClientNotFoundException;
import com.qa.clientsspringboot.exceptions.EmailTakenException;
import com.qa.clientsspringboot.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ClientServiceInterface{

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    //Create
    @Override
    public Client createClient(Client client) {
        //Checking that email is not taken
        Optional<Client> emailTaken = this.clientRepository.findClientByEmail(client.getEmail());
        if (emailTaken.isPresent()){
            throw new EmailTakenException(String.format("Email %s is already taken", client.getEmail()));
        }
        //saving to database and returning
        return clientRepository.save(client);
    }


    //Read
    @Override
    public Client getClientById(Long id) {
        //Checking that client with that id is in database
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new ClientNotFoundException(String.format("Client with ID: %s not found", id));
        }
        //returning client
        return optionalClient.get();
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

//    @Override
//    public List<Client> getClientsByName(String name) {
//        return clientRepository.findClientsByName(name);
//    }

    @Override
    public Client getClientByEmail(String email) {
        Optional<Client> optionalClient = this.clientRepository.findClientByEmail(email);
        if (optionalClient.isEmpty()) {
            throw new ClientNotFoundException(String.format("Client with email: %s not found", email));
        }
        return optionalClient.get();
    }



    //Update

    @Override
    public Client updateClient(Long id, Client client) {
        Optional<Client> toBeUpdated = clientRepository.findById(id);

        if (toBeUpdated.isEmpty()) {
            throw new ClientNotFoundException(String.format("Client with ID: %s not found", id));
        }

        Client present = toBeUpdated.get();

        //if email does not change, and email is not taken by someone else, update, else throw error
        if (present.getEmail().equals(client.getEmail()) || this.clientRepository.findClientByEmail(client.getEmail()).isEmpty()) {
            present.setDob(client.getDob());
            present.setName(client.getName());
            present.setEmail(client.getEmail());
            return clientRepository.save(present);
        }
        else {
            throw new EmailTakenException(String.format("Email %s is already taken", client.getEmail()));
        }

    }

    @Override
    public Client deleteClient(Long id) {
        Optional<Client> toBeDeleted = clientRepository.findById(id);
        if (toBeDeleted.isEmpty()){
            throw new ClientNotFoundException(String.format("Client with ID: %s not found", id));
        }

        clientRepository.deleteById(id);
        return toBeDeleted.get();

    }
}
