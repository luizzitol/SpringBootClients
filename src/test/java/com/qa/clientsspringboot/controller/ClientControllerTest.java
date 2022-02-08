package com.qa.clientsspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.clientsspringboot.domain.Client;
import com.qa.clientsspringboot.exceptions.ErrorResponse;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:client-schema.sql", "classpath:client-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ClientControllerTest {

    Client included;
    Client test1ID;
    Client test1IDcopy;
    Client test2ID;



    String test1;
    String test2;



    ErrorResponse emailTaken;
    ErrorResponse clientNotFound;


    @BeforeEach
    public void testSetUp() {
        included = new Client(1L, "luis", "luis@test.com", LocalDate.of(1990, 7, 23));

        test1ID = new Client(2L, "luis", "luis1@test.com", LocalDate.of(1990, 1, 2));
        test1 = "{\"name\" : \"luis\",\"email\": \"luis1@test.com\",\"dob\" : \"1990-01-01\"}";

        test2ID = new Client(3L, "luis2", "luis2@test.com", LocalDate.of(1990, 1, 2));
        test2 = "{\"name\" : \"luis\",\"email\": \"luis2@test.com\",\"dob\" : \"1990-01-01\"}";

        emailTaken = new ErrorResponse("Email luis1@test.com is already taken");
        clientNotFound = new ErrorResponse("Client with ID: 6 not found");

    }

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper map;

    @Test
    public void createClientTest() throws Exception {
        this.mock
                .perform(post("/api/v1/client").contentType(MediaType.APPLICATION_JSON).content(test1))
                .andExpect(status().isCreated())
                .andExpect(content().json(this.map.writeValueAsString(test1ID)));

        this.mock
                .perform(post("/api/v1/client").contentType(MediaType.APPLICATION_JSON).content(test1))
                .andExpect(status().isForbidden())
                .andExpect(content().json(this.map.writeValueAsString(emailTaken)));



    }

    @Test
    public void readClientTest() throws Exception {
        this.mock
                .perform(get("/api/v1/client"))
                .andExpect(status().isOk())
                .andExpect(content().json(this.map.writeValueAsString(List.of(included))));

    }

    @Test
    public void readClientById() throws Exception {
        this.mock
                .perform(get("/api/v1/client/1"))
                .andExpect(status().isFound())
                .andExpect(content().json(this.map.writeValueAsString(included)));

        this.mock
                .perform(get("/api/v1/client/6"))
                .andExpect((status().isNotFound()))
                .andExpect(content().json(this.map.writeValueAsString(clientNotFound)));
    }

    @Test
    public void updateClient() throws Exception {
        test1ID.setId(1L);
        this.mock
                .perform(put("/api/v1/client/1").contentType(MediaType.APPLICATION_JSON).content(test1))
                .andExpect(status().isAccepted())
                .andExpect(content().json(this.map.writeValueAsString(test1ID)));

        this.mock
                .perform(put("/api/v1/client/6").contentType(MediaType.APPLICATION_JSON).content(test1))
                .andExpect((status().isNotFound()))
                .andExpect(content().json(this.map.writeValueAsString(clientNotFound)));
    }


    @Test
    public void deleteClient() throws Exception {
        this.mock
                .perform(delete("/api/v1/client/1"))
                .andExpect(status().isAccepted())
                .andExpect(content().json(this.map.writeValueAsString(included)));

        this.mock
                .perform(delete("/api/v1/client/6").contentType(MediaType.APPLICATION_JSON).content(test1))
                .andExpect((status().isNotFound()))
                .andExpect(content().json(this.map.writeValueAsString(clientNotFound)));
    }

    @Test
    public void testEqual() {
        EqualsVerifier.simple().forClass(Client.class).verify();
    }

}
