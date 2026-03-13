package com.bespalov.nail_service.exceptions;

public class DbException extends RuntimeException {
    public DbException(String message) {
        super(message);
    }
}
