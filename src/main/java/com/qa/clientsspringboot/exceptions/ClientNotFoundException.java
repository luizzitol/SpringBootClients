package com.qa.clientsspringboot.exceptions;

public class ClientNotFoundException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }


    public ClientNotFoundException(String message) {
        this.message = message;
    }
}
