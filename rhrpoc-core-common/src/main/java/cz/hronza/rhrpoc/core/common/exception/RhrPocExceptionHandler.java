package cz.hronza.rhrpoc.core.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cz.hronza.rhrpoc.core.common.utils.RequestUtils.getCorrelationId;
import static cz.hronza.rhrpoc.core.common.utils.RequestUtils.getRandomUuid;

@ControllerAdvice
public abstract class RhrPocExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RhrPocExceptionHandler.class);

    @ExceptionHandler(RhrCannotBeDividedByZero.class)
    public ResponseEntity<Object> rhrCannotBeDividedByZeroExceptionHandler(RhrCannotBeDividedByZero ex) {
        return logExceptionAndCreateResponse("RHR_CANNOT_BE_DIVIDED_BY_ZERO", ex, HttpStatus.BAD_REQUEST);
    }

    ResponseEntity<Object> logExceptionAndCreateResponse(String emsg, RuntimeException exception, HttpStatus httpStatus) {
        return logExceptionAndCreateResponse(emsg, exception, httpStatus, Collections.emptyList());

    }

    ResponseEntity<Object> logExceptionAndCreateResponse(String emsg, RuntimeException exception, HttpStatus httpStatus, List<ErrorParameterDto> errors) {
        logexception(emsg, exception, errors);
        return ResponseEntity
                .status(httpStatus)
                .body(createError(emsg)
                        .parameters(errors));
    }

    private void logexception(String emsg, RuntimeException e, List<ErrorParameterDto> errors) {
        log.error(emsg, e.getMessage(), errors);
    }

    private ErrorItemDto createError(String code, ErrorParameterDto... parameterDtos) {
        return new ErrorDto()
                .collerationId(getCorrelationId())
                .time(LocalDateTime.now().atZone(ZoneId.systemDefault()).toOffsetDateTime())
                .uuid(getRandomUuid())
                .code((code))
                .severity(ErrorItemDto.SeverityEnum.ERROR)
                .parameters(
                        Stream.of(parameterDtos).filter(Objects::nonNull).collect(Collectors.toList())
                );
    }
}
