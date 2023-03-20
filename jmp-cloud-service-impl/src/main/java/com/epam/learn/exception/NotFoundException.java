package com.epam.learn.exception;

/**
 * An exception thrown to indicate that an attempt has been made to access an object that does not exist. Status code 404.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
