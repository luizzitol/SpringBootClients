package com.qa.clientsspringboot.service;

import com.qa.clientsspringboot.domain.Client;
import com.qa.clientsspringboot.exceptions.ClientNotFoundException;
import com.qa.clientsspringboot.exceptions.EmailTakenException;
import com.qa.clientsspringboot.repo.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class ClientServiceTest {

    Client test1noID;
    Client test1ID;
    Client test2noID;
    Client test2ID;

    @Autowired
    private ClientService service;

    @MockBean
    private ClientRepository repo;

    @BeforeEach
    public void testSetUp() {
        test1noID = new Client("luis", "luis@test.com", LocalDate.of(1990, 1, 2));
        test2noID = new Client("luis2", "luis2@test.com", LocalDate.of(1990, 1, 2));
        test1ID = new Client(1L, "luis", "luis@test.com", LocalDate.of(1990, 1, 2));
        test2ID = new Client(2L, "luis2", "luis2@test.com", LocalDate.of(1990, 1, 2));
    }

    @Test
    public void createClientTest() {

        Mockito.when(this.repo.save(test1noID)).thenReturn(test1ID);

        assertThat(this.service.createClient(test1noID)).isEqualTo(test1ID);

        Mockito.verify(this.repo, Mockito.times(1)).save(test1noID);
        Mockito.verify(this.repo, Mockito.times(1)).findClientByEmail(test1noID.getEmail());


    }

    @Test
    public void createClientException() {
        Mockito.when(this.repo.findClientByEmail(test1noID.getEmail())).thenReturn(Optional.ofNullable(test1ID));
        EmailTakenException thrown = Assertions.assertThrows(EmailTakenException.class, () -> this.service.createClient(test1noID));
        Assertions.assertEquals("Email luis@test.com is already taken", thrown.getMessage());
        Mockito.verify(this.repo, Mockito.times(1)).findClientByEmail(test1noID.getEmail());

    }

    @Test
    public void getClientByIdTest() {
        Mockito.when(this.repo.findById(1l)).thenReturn(Optional.ofNullable(test1ID));
        assertThat(this.service.getClientById(1L)).isEqualTo(test1ID);
        Mockito.verify(this.repo, Mockito.times(1)).findById(1L);

    }

    @Test
    public void getClientByIdExceptionTest() {
        Mockito.when(this.repo.findById(1L)).thenReturn(Optional.ofNullable(null));
        ClientNotFoundException thrown = Assertions.assertThrows(ClientNotFoundException.class, () -> this.service.getClientById(1L));
        assertThat("Client with ID: 1 not found").isEqualTo(thrown.getMessage());
        Mockito.verify(this.repo, Mockito.times(1)).findById(1L);

    }

    @Test
    public void getAllClientsTest() {
        List<Client> toBeReturned = List.of(test1ID, test2ID);
        Mockito.when(this.repo.findAll()).thenReturn(toBeReturned);
        assertThat(this.service.getAllClients()).isEqualTo(toBeReturned);
        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }

    @Test
    public void getClientByEmailTest() {
        Mockito.when(this.repo.findClientByEmail(test1ID.getEmail())).thenReturn(Optional.ofNullable(test1ID));

        assertThat(this.service.getClientByEmail(test1ID.getEmail())).isEqualTo(test1ID);

        Mockito.verify(this.repo, Mockito.times(1)).findClientByEmail(test1ID.getEmail());
    }

    @Test
    public void getClientByEmailException() {
        Mockito.when(this.repo.findClientByEmail(test1ID.getEmail())).thenReturn(Optional.ofNullable(null));

        ClientNotFoundException thrown = Assertions.assertThrows(ClientNotFoundException.class, () -> this.service.getClientByEmail(test1noID.getEmail()));
        assertThat("Client with email: luis@test.com not found").isEqualTo(thrown.getMessage());
        Mockito.verify(this.repo, Mockito.times(1)).findClientByEmail(test1noID.getEmail());
    }

    @Test
    public void updateClientTest() {
        Client empty = new Client(3L, "null", "null", null);
        Client updated = new Client(3L, test1ID.getName(), test1ID.getEmail(), test1ID.getDob());

        Mockito.when(this.repo.findById(3L)).thenReturn(Optional.ofNullable(empty));

        Mockito.when(this.repo.save(updated)).thenReturn(updated);

        assertThat(this.service.updateClient(3L, test1noID)).isEqualTo(updated);
    }

    @Test
    public void updateClientNotFoundException() {
        Mockito.when(this.repo.findById(1L)).thenReturn(Optional.ofNullable(null));
        ClientNotFoundException thrown = Assertions.assertThrows(ClientNotFoundException.class, () -> this.service.updateClient(1L, test2noID));
        assertThat("Client with ID: 1 not found").isEqualTo(thrown.getMessage());
        Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
    }

    @Test
    public void updateClientEmailTakenException() {
        Mockito.when(this.repo.findById(1L)).thenReturn(Optional.ofNullable(test1ID));
        Mockito.when(this.repo.findClientByEmail(test2ID.getEmail())).thenReturn(Optional.ofNullable(test1ID));
        EmailTakenException thrown = Assertions.assertThrows(EmailTakenException.class, () -> this.service.updateClient(1L, test2noID));
        Assertions.assertEquals("Email luis2@test.com is already taken", thrown.getMessage());
    }

    @Test
    public void deleteClientTest() {
        Mockito.when(this.repo.findById(1L)).thenReturn(Optional.ofNullable(test1ID));
        assertThat(this.service.deleteClient(1L)).isEqualTo(test1ID);
        Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteClientException() {
        Mockito.when(this.repo.findById(1L)).thenReturn(Optional.ofNullable(null));
        ClientNotFoundException thrown = Assertions.assertThrows(ClientNotFoundException.class, () -> this.service.deleteClient(1L));
        assertThat("Client with ID: 1 not found").isEqualTo(thrown.getMessage());
    }
}
