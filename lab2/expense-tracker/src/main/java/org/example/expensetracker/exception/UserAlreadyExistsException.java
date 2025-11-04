package org.example.expensetracker.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String name) {
        super("User with name already exists: " + name);
    }
}