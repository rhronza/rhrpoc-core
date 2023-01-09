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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static cz.hronza.rhrpoc.core.common.utils.RequestUtils.getCorrelationId;
import static cz.hronza.rhrpoc.core.common.utils.RequestUtils.getRandomUuid;

//@ControllerAdvice
public abstract class RhrPocExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String ERROR_MESSAGE_RHR_CANNOT_BE_DIVIDED_BY_ZERO = "CANNOT_BE_DIVIDED_BY_ZERO";
    public static final String ERROR_MESSAGE_RHR_POC_NOT_SAVED_EXCEPTION = "NOT_SAVED";
    public static final String ERROR_MESSAGE_RHR_POC_NOT_FOUND_EXCEPTION = "NOT_FOUND";

    private static final Logger log = LoggerFactory.getLogger(RhrPocExceptionHandler.class);


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
        log.error("handleMissingPathVariable: status={} {}, message={}, parameter={}", status.value(), status.getReasonPhrase(), ex.getMessage(), ex.getParameter());
        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("handleTypeMismatch: status={} {}, message={}, property name={}", status.value(), status.getReasonPhrase(), ex.getMessage(), ex.getPropertyName());
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("handleMissingServletRequestParameter: status={} {}, message={}, parameter name={}", status.value(), status.getReasonPhrase(), ex.getMessage(), ex.getParameterName());
        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("handleHttpMessageNotReadable: status={} {}, message={}, http input message={}", status.value(), status.getReasonPhrase(), ex.getMessage(), ex.getHttpInputMessage());
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("handleMethodArgumentNotValid: status={} {}, message={}, parameter={}", status.value(), status.getReasonPhrase(), ex.getMessage(), ex.getParameter());
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ExceptionHandler(RhrPocCannotBeDividedByZero.class)
    public ResponseEntity<Object> rhrCannotBeDividedByZeroExceptionHandler(RhrPocCannotBeDividedByZero ex) {
        return logExceptionAndCreateResponse(
                ERROR_MESSAGE_RHR_CANNOT_BE_DIVIDED_BY_ZERO,
                ex,
                HttpStatus.BAD_REQUEST,
                Collections.singletonList(new ErrorParameterDto().key(ex.getParams().get(0)).value(ex.getParams().get(1))));
    }

    @ExceptionHandler(RhrPocNotSavedException.class)
    public ResponseEntity<Object> rhrPocNotSavedExceptionHandler(RhrPocNotSavedException ex) {
        List<ErrorParameterDto> errorParameterDtos =
                ex.getParameters()
                        .stream()
                        .map(parameter -> new ErrorParameterDto().setKey(parameter.key()).setValue(parameter.value()))
                        .toList();

        return logExceptionAndCreateResponse(
                ERROR_MESSAGE_RHR_POC_NOT_SAVED_EXCEPTION,
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                errorParameterDtos);
    }

    @ExceptionHandler(RhrPocNotFoundException.class)
    public ResponseEntity<Object> rhrPocNotFoundExceptionHandler(RhrPocNotFoundException ex) {
        List<ErrorParameterDto> errorParameterDtos =
                ex.getParameters()
                        .stream()
                        .map(parameter -> new ErrorParameterDto().setKey(parameter.key()).setValue(parameter.value()))
                        .toList();

        return logExceptionAndCreateResponse(
                ERROR_MESSAGE_RHR_POC_NOT_FOUND_EXCEPTION,
                ex,
                HttpStatus.NOT_FOUND,
                errorParameterDtos);
    }

    @ExceptionHandler(RhrPocAccessForbidenException.class)
    public ResponseEntity<Object> rhrCannotBeDividedByZeroExceptionHandler(RhrPocAccessForbidenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
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
        log.error(emsg, e.getMessage(), params);
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
