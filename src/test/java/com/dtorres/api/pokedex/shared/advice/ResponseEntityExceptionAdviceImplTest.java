package com.dtorres.api.pokedex.shared.advice;

import com.dtorres.api.pokedex.commons.domain.exception.NotFoundDataException;
import com.dtorres.api.pokedex.commons.domain.exception.TechnicalException;
import com.dtorres.api.pokedex.shared.dto.DtoResponseEntityException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ResponseEntityExceptionAdviceImplTest {

    private static final String MESSAGE_ERROR = "This is an error";
    private static final int STATUS_404 = 404;

    @InjectMocks
    ResponseEntityExceptionAdviceImpl responseExceptionAdviceImpl;

    @Test
    public void processStatus404ForTechnicalExceptionTest() {
        ResponseEntity<DtoResponseEntityException> exception = responseExceptionAdviceImpl.processStatus404ForTechnicalException(new TechnicalException(MESSAGE_ERROR));
        assertEquals(STATUS_404, exception.getStatusCodeValue());
    }

    @Test
    public void processStatus404ForNotFoundDataExceptionTest() {
        ResponseEntity<DtoResponseEntityException> exception = responseExceptionAdviceImpl.processStatus404ForNotFoundDataException(new NotFoundDataException(MESSAGE_ERROR));
        assertEquals(STATUS_404, exception.getStatusCodeValue());
    }
}
