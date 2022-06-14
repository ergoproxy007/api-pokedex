package com.dtorres.api.pokedex.commons.helper;

import com.dtorres.api.pokedex.commons.domain.exception.DominioException;
import com.dtorres.api.pokedex.commons.log.LogErrorManager;

import java.util.List;

import static java.util.Collections.emptyList;

public abstract class ThrowExceptionHelper {

    public static <T> T throwException(Throwable throwable, Object object, DominioException domainException) {
        if(throwable != null) {
            LogErrorManager.error(domainException.getMessage(), throwable);
            throw domainException;
        }
        return (T) object;
    }

    public static <T> List<T> throwException(Throwable throwable, DominioException domainException) {
        if(throwable != null) {
            LogErrorManager.error(domainException.getMessage(), throwable);
            throw domainException;
        }
        return emptyList();
    }
}
