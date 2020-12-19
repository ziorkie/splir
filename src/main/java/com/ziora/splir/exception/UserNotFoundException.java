package com.ziora.splir.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s){
        super(s);
    }
}
