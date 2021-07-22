package com.dwp.userlocationapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
    private final MessageSource messageSource;

    @Inject
    public ApplicationExceptionHandler(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<ErrorResponse> exceptionHandler(final ConstraintViolationException ex) {
        final ErrorResponse error = getBasicResponse(ex);
        LOGGER.error(error.toString(), ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorResponse> exceptionHandler(final MethodArgumentNotValidException ex) {
        final ErrorResponse error = getBasicResponse(ex);
        LOGGER.error(error.toString(), ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ServiceNotAvailableException.class })
    public ResponseEntity<ErrorResponse> exceptionHandler(final ServiceNotAvailableException ex) {
        final ErrorResponse error = getBasicResponse(ex);
        LOGGER.error(error.toString(), ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse getBasicResponse(Exception exception){
        final ErrorResponse error = new ErrorResponse();
        error.setMessage(exception.getLocalizedMessage());
        error.setService("User location Service");
        error.setType("Error Response");
        return error;
    }
}
