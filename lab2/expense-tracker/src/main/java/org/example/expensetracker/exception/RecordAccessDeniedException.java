package org.example.expensetracker.exception;

public class RecordAccessDeniedException extends RuntimeException {
    public RecordAccessDeniedException(String message) {
        super(message);
    }
}