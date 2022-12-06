package cz.hronza.rhrpoc.core.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static cz.hronza.rhrpoc.core.common.utils.RequestUtils.getCorrelationId;
import static cz.hronza.rhrpoc.core.common.utils.RequestUtils.getRandomUuid;

@ControllerAdvice
public abstract class RhrPocExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String ERROR_MESSAGE_RHR_CANNOT_BE_DIVIDED_BY_ZERO = "CANNOT_BE_DIVIDED_BY_ZERO";

    private static final Logger log4j = LoggerFactory.getLogger(RhrPocExceptionHandler.class);


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported
            (HttpRequestMethodNotSupportedException ex,
             HttpHeaders headers,
             HttpStatus status,
             WebRequest request) {
        List<ErrorItemDto> errorItemDtos = List.of(new ErrorItemDto().code("444"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(new ErrorDto().errorItemDtos(errorItemDtos));
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log4j.error("handleMissingPathVariable: status={} {}, message={}, parameter={}", status.value(), status.getReasonPhrase(), ex.getMessage(), ex.getParameter());
        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log4j.error("handleTypeMismatch: status={} {}, message={}, property name={}", status.value(), status.getReasonPhrase(), ex.getMessage(), ex.getPropertyName());
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log4j.error("handleMissingServletRequestParameter: status={} {}, message={}, parameter name={}", status.value(), status.getReasonPhrase(), ex.getMessage(), ex.getParameterName());
        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log4j.error("handleHttpMessageNotReadable: status={} {}, message={}, http input message={}", status.value(), status.getReasonPhrase(), ex.getMessage(), ex.getHttpInputMessage());
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log4j.error("handleMethodArgumentNotValid: status={} {}, message={}, parameter={}", status.value(), status.getReasonPhrase(), ex.getMessage(), ex.getParameter());
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ExceptionHandler(RhrCannotBeDividedByZero.class)
    public ResponseEntity<Object> rhrCannotBeDividedByZeroExceptionHandler(RhrCannotBeDividedByZero ex) {
        return logExceptionAndCreateResponse(
                ERROR_MESSAGE_RHR_CANNOT_BE_DIVIDED_BY_ZERO,
                ex,
                HttpStatus.BAD_REQUEST,
                Collections.singletonList(new ErrorParameterDto().key(ex.getParams().get(0)).value(ex.getParams().get(1))));
    }

    ResponseEntity<Object> logExceptionAndCreateResponse(String emsg, RuntimeException exception, HttpStatus httpStatus) {
        return logExceptionAndCreateResponse(emsg, exception, httpStatus, Collections.emptyList());

    }

    ResponseEntity<Object> logExceptionAndCreateResponse(String emsg, RuntimeException exception, HttpStatus httpStatus, List<ErrorParameterDto> params) {
        logexception(emsg, exception, params);
        return ResponseEntity
                .status(httpStatus)
                .body(createError(emsg, params));
    }

    private void logexception(String emsg, RuntimeException e, List<ErrorParameterDto> params) {
        log4j.error(emsg, e.getMessage(), params);
    }

    private ErrorDto createError(String code, List<ErrorParameterDto> params) {
        return new ErrorDto()
                .errorItemDtos(
                        Collections.singletonList(new ErrorItemDto()
                                .collerationId(getCorrelationId())
                                .time(LocalDateTime.now().atZone(ZoneId.systemDefault()).toOffsetDateTime())
                                .uuid(getRandomUuid())
                                .code((code))
                                .severity(ErrorItemDto.SeverityEnum.ERROR)
                                .parameters(params)
                        )
                );
    }
}
