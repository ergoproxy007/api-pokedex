package com.dtorres.api.pokedex.query.infrastructure.exception.helper;

import com.dtorres.api.pokedex.commons.domain.exception.DominioException;
import com.dtorres.api.pokedex.commons.domain.exception.ErrorTecnicoException;
import com.dtorres.api.pokedex.commons.helper.ThrowExceptionHelper;
import com.dtorres.api.pokedex.commons.log.LogErrorManager;

import java.util.List;

public class QueryExceptionHelper extends ThrowExceptionHelper {

    private static final String DEFAULT_MESSAGE = "Not enough information";

    private static ErrorTecnicoException getErrorTecnicoException(Throwable throwable) {
        LogErrorManager.error(DEFAULT_MESSAGE, throwable);

        if (throwable.getCause() != null && throwable.getCause() instanceof DominioException) {
            return new ErrorTecnicoException(throwable.getCause().getMessage());
        }
        if (throwable instanceof DominioException) {
            return new ErrorTecnicoException(throwable.getCause().getMessage());
        }
        return new ErrorTecnicoException(DEFAULT_MESSAGE);
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
