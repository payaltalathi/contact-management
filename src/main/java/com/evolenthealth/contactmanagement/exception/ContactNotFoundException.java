package com.evolenthealth.contactmanagement.exception;

public class ContactNotFoundException extends RuntimeException{

    public ContactNotFoundException(String message){
        super(message);
    }
}
