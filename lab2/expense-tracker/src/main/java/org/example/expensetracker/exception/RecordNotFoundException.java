package org.example.expensetracker.exception;

import java.util.UUID;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(UUID id) {
        super("Could not find record with id: " + id);
    }
}