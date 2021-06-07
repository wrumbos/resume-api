package com.wcode.resume.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.ZonedDateTime;

@Data
public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final String timestamp;

    public ApiException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = Instant.now().toString();
    }
}
