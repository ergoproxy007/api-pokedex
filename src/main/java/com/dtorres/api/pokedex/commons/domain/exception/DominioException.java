package com.dtorres.api.pokedex.commons.domain.exception;

public class DominioException extends ResponseEntityException {

    public DominioException(String message) {
        super(message);
    }
}
