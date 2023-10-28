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

    private ResponseEntity<ErrorMessage> ErrorResponse(HttpStatus status, Exception ex, WebRequest request) {
        log.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);

        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(status.value());
        message.setHttpStatus(status.toString());
        message.setTimestamp(OffsetDateTime.now());
        message.setMessage(ex.getMessage());
        message.setDescription(request.getDescription(false));

        return ResponseEntity.status(status).body(message);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return ErrorResponse(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(ObjectAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleObjectAlreadyExistException (ObjectAlreadyExistException ex, WebRequest request) {
        return ErrorResponse(HttpStatus.CONFLICT, ex, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleDefaultException (Exception ex, WebRequest request) {
        return ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }
}
