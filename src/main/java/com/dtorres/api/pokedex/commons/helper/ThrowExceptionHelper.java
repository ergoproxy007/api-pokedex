package com.dtorres.api.pokedex.commons.helper;

import com.dtorres.api.pokedex.commons.domain.exception.DomainException;
import com.dtorres.api.pokedex.commons.log.LogErrorManager;

import java.util.List;

import static java.util.Collections.emptyList;

public abstract class ThrowExceptionHelper {

    public static <T> T throwException(Throwable throwable, Object object, DomainException domainException) {
        if(throwable != null) {
            LogErrorManager.error(domainException.getMessage(), throwable);
            throw domainException;
        }
        return (T) object;
    }

    public static <T> List<T> throwException(Throwable throwable, DomainException domainException) {
        if(throwable != null) {
            LogErrorManager.error(domainException.getMessage(), throwable);
            throw domainException;
        }
        return emptyList();
    }
}
