package com.dwp.userlocationapi.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ApplicationExceptionHandlerTest {

    public static final String USER_LOCATION_SERVICE = "User location Service";
    public static final String ERROR_RESPONSE = "Error Response";
    private ApplicationExceptionHandler applicationExceptionHandler;

    @Mock
    private MessageSource messageSource;

    @Mock
    private MethodArgumentNotValidException mve;

    @Mock
    private ConstraintViolationException cve;

    @Mock
    private BindingResult bindingResult;

    @Before
    public void setUp() {
        applicationExceptionHandler = new ApplicationExceptionHandler(messageSource);
    }


    @Test
    public void exceptionHandlerInvalidMessageException() {
        final ResponseEntity<ErrorResponse> response = applicationExceptionHandler
                .exceptionHandler((new ServiceNotAvailableException("Service not available")));

        assertEquals(USER_LOCATION_SERVICE, response.getBody().getService());
        assertEquals(ERROR_RESPONSE, response.getBody().getType());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void exceptionHandlerMethodArgumentNotValidException() {
        final ResponseEntity<ErrorResponse> response = applicationExceptionHandler
                .exceptionHandler(mve);

        assertEquals(USER_LOCATION_SERVICE, response.getBody().getService());
        assertEquals(ERROR_RESPONSE, response.getBody().getType());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void exceptionHandlerConstraintViolationException() {
        final ResponseEntity<ErrorResponse> response = applicationExceptionHandler
                .exceptionHandler(cve);

        assertEquals(USER_LOCATION_SERVICE, response.getBody().getService());
        assertEquals(ERROR_RESPONSE, response.getBody().getType());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}