package com.medicalcenter.roleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private HttpStatus httpStatus;
    private Date timestamp;
    private String message;
    private String description;
}
