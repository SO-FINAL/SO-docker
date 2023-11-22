package com.group04.docker.utils.exception;

public class ValidationException extends RuntimeException{
    public ValidationException() {
        super();
    }

    public ValidationException(String message){
        super(message);
    }

}
