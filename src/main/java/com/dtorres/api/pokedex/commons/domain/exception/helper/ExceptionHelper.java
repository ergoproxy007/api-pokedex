package com.dtorres.api.pokedex.commons.domain.exception.helper;

import com.dtorres.api.pokedex.commons.domain.exception.DomainException;
import com.dtorres.api.pokedex.commons.domain.exception.TechnicalException;
import com.dtorres.api.pokedex.commons.helper.ThrowExceptionHelper;
import com.dtorres.api.pokedex.commons.log.LogErrorManager;

import java.util.List;

public class ExceptionHelper extends ThrowExceptionHelper {

    private static final String DEFAULT_MESSAGE = "Not enough information";

    private static TechnicalException getErrorTecnicoException(Throwable throwable) {
        LogErrorManager.error(DEFAULT_MESSAGE, throwable);

        if (throwable.getCause() != null && throwable.getCause() instanceof DomainException) {
            return new TechnicalException(throwable.getCause().getMessage());
        }
        if (throwable instanceof DomainException) {
            return new TechnicalException(throwable.getCause().getMessage());
        }
        return new TechnicalException(DEFAULT_MESSAGE);
    }

    public static <T> T throwObject(Throwable throwable) {
        return throwException(throwable, new Object(), getErrorTecnicoException(throwable));
    }

    public static <T> T throwObject(Throwable throwable, Object object) {
        return throwException(throwable, object, getErrorTecnicoException(throwable));
    }

    public static <T> List<T> throwList(Throwable throwable) {
        return throwException(throwable, getErrorTecnicoException(throwable));
    }
}
