package com.medicalcenter.roleservice.exception;

public class ObjectAlreadyExistException extends RuntimeException{
    public ObjectAlreadyExistException(String message) {
        super(message);
    }
}
