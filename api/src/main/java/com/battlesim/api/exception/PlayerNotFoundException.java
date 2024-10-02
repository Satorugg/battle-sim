package com.battlesim.api.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(Long id) {
        super("Could not find player " + id);
    }
    
}
