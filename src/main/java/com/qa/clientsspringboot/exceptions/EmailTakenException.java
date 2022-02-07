package com.qa.clientsspringboot.exceptions;

public class EmailTakenException extends RuntimeException{

    private String message;

    public EmailTakenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
