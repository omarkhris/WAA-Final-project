package com.miu.pmtbackendapi.exception.exceptionhandler;

import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.exception.CustomMessage;
import com.miu.pmtbackendapi.exception.customexception.UserDeactivedException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseStatus
public class ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> itemNotFoundException(ItemNotFoundException ex, WebRequest wq) {
        CustomMessage customMessage = new CustomMessage(ex.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(customMessage);
    }

    @ExceptionHandler(value = BadCredentialsException.class) // when Invalid Credentials
    public ResponseEntity<ErrorMessage> handleInvalidCredentialsException(
            BadCredentialsException e, WebRequest wq) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UserDeactivedException.class) // when Invalid Credentials
    public ResponseEntity<ErrorMessage> handleUserDeactivedException(
            UserDeactivedException e, WebRequest wq) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = RuntimeException.class) // when Invalid Credentials
    public ResponseEntity<ErrorMessage> handleRuntimeException(
            RuntimeException e, WebRequest wq) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
