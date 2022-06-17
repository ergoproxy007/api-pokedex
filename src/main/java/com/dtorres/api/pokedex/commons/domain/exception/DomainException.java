package com.dtorres.api.pokedex.commons.domain.exception;

public class DomainException extends ResponseEntityException {

    public DomainException(String message) {
        super(message);
    }
}
