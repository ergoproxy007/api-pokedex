package com.dtorres.api.pokedex.shared.advice;

import com.dtorres.api.pokedex.commons.domain.exception.ResponseEntityException;
import com.dtorres.api.pokedex.shared.dto.DtoResponseEntityException;
import org.springframework.http.ResponseEntity;

public abstract class ResponseEntityExceptionAdvice {

    public ResponseEntity<DtoResponseEntityException> processStatus(ResponseEntityException responseEntityException, int codeStatus) {
        return ResponseEntity.status(codeStatus).body(new DtoResponseEntityException(codeStatus, responseEntityException.getMessage()));
    }
}
