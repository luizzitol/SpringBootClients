package com.qa.clientsspringboot.controller;

import com.qa.clientsspringboot.service.ClientServiceInterface;

public class ClientController {
    private ClientServiceInterface clientService;


    public ClientController(ClientServiceInterface clientService) {
        this.clientService = clientService;
    }


}
