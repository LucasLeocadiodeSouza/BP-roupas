package com.bphost.principal.exception;

public class UserListNotFoundException extends RuntimeException{
    public UserListNotFoundException(String message){
        super(message);
    }
}
