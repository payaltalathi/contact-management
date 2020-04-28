package com.evolenthealth.contactmanagement.Exception;

public class ContactNotFoundException extends RuntimeException{

    public ContactNotFoundException(String message){
        super(message);
    }
}
