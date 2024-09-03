package com.ejercicio15.exception;

public class DataBaseException extends RuntimeException {

    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

}