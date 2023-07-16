package com.medicalcenter.roleservice.hendler;

import com.medicalcenter.roleservice.dto.ErrorMessage;
import com.medicalcenter.roleservice.exception.ObjectAlreadyExistException;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        log.error("ResourceNotFoundException: {}", ex.getMessage(), ex);

        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleObjectAlreadyExistException (ObjectAlreadyExistException ex, WebRequest request) {
        log.error("ObjectAlreadyExistException: {}", ex.getMessage(), ex);

        ErrorMessage message = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT,
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
}
