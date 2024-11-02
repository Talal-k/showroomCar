package com.example.carshowroom.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final String messageKey;

    public ResourceNotFoundException(String messageKey) {
        super(messageKey); // Set message key as the exception message
        this.messageKey = messageKey;
    }

}
