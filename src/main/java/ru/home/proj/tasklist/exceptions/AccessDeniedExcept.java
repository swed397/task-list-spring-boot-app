package ru.home.proj.tasklist.exceptions;

public class AccessDeniedExcept extends RuntimeException{

    public AccessDeniedExcept(String message) {
        super(message);
    }
}
