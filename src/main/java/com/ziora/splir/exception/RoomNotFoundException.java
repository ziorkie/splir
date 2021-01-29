package com.ziora.splir.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String s){
        super(s);
    }
}