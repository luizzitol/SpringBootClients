package com.qa.clientsspringboot.controller;

import com.qa.clientsspringboot.service.ClientServiceInterface;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
@CrossOrigin
public class ClientController {
    private ClientServiceInterface clientService;


    public ClientController(ClientServiceInterface clientService) {
        this.clientService = clientService;
    }




}
