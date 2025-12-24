package com.bphost.principal.exception;

public class UserCartNotFoundException extends RuntimeException{
    public UserCartNotFoundException(String message){
        super(message);
    }
}
