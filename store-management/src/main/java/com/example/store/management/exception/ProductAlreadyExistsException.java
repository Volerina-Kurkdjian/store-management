package com.example.store.management.exception;

public class ProductAlreadyExistsException extends RuntimeException{

    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
