package com.dtorres.api.pokedex.shared.advice;

import com.dtorres.api.pokedex.commons.domain.exception.NotFoundDataException;
import com.dtorres.api.pokedex.commons.domain.exception.TechnicalException;
import com.dtorres.api.pokedex.shared.dto.DtoResponseEntityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityExceptionAdviceImpl extends ResponseEntityExceptionAdvice {

    private static final int STATUS_404 = 404;

    @ExceptionHandler(NotFoundDataException.class)
    public ResponseEntity<DtoResponseEntityException> processStatus404ForNotFoundDataException(NotFoundDataException exception) {
        return processStatus(exception, STATUS_404);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<DtoResponseEntityException> processStatus404ForTechnicalException(TechnicalException exception) {
        return processStatus(exception, STATUS_404);
    }
}
