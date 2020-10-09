package com.example.bookrecord.exception;

public class ResourceDoseNotExistException extends Exception {
    public ResourceDoseNotExistException(String resource) {
        super(resource + "dose not exists!");
    }
}
