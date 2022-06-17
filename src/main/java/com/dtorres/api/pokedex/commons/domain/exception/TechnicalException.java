package com.dtorres.api.pokedex.commons.domain.exception;

public class TechnicalException extends DomainException {

    private static final long serialVersionUID = 1L;

    public TechnicalException(String message) {
        super(message);
    }
}
