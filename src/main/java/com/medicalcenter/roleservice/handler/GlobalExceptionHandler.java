package com.medicalcenter.roleservice.handler;

import io.tej.SwaggerCodgen.model.ErrorMessage;
import com.medicalcenter.roleservice.exception.ObjectAlreadyExistException;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        log.error("ResourceNotFoundException: {}", ex.getMessage(), ex);

        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.NOT_FOUND.value());
        message.setHttpStatus(HttpStatus.NOT_FOUND.toString());
        message.setTimestamp(OffsetDateTime.now());
        message.setMessage(ex.getMessage());
        message.setDescription(request.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ObjectAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleObjectAlreadyExistException (ObjectAlreadyExistException ex, WebRequest request) {
        log.error("ObjectAlreadyExistException: {}", ex.getMessage(), ex);

        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.CONFLICT.value());
        message.setHttpStatus(HttpStatus.CONFLICT.toString());
        message.setTimestamp(OffsetDateTime.now());
        message.setMessage(ex.getMessage());
        message.setDescription(request.getDescription(false));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleDefaultException (Exception ex, WebRequest request) {
        log.error("Unexpected exception: {}", ex.getMessage(), ex);

        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        message.setTimestamp(OffsetDateTime.now());
        message.setMessage(ex.getMessage());
        message.setDescription(request.getDescription(false));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
