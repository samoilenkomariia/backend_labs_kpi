package org.example.expensetracker.exception;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(UUID id) {
        super("Could not find category with id: " + id);
    }
}
