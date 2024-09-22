package com.example.store.management.exception;

public class StoreNotFoundException extends RuntimeException {

    public StoreNotFoundException(String message){
        super(message);
    }
}
