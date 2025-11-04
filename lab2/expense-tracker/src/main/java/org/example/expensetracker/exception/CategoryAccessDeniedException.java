package org.example.expensetracker.exception;

public class CategoryAccessDeniedException extends RuntimeException {
    public CategoryAccessDeniedException(String message) {
        super(message);
    }
}
