package com.cifolio.cifolio.exception_handling.exceptions;

public class UnauthenticatedUserException extends RuntimeException {
    public UnauthenticatedUserException(String message) {
        super(message);
    }
}
