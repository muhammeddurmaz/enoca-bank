package com.challenge.enocabank.errors;

public class BadRequestAlertException extends RuntimeException{

    public BadRequestAlertException(String message){
        super(message);
    }
}
