package com.wcode.resume.exception;

import com.wcode.resume.model.response.ApiException;
import com.wcode.resume.serviceImpl.AuthServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.wcode.resume.constant.ConferenceConstant.LOG_ERROR_EXCEPTION_HANDLER;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        logger.error(LOG_ERROR_EXCEPTION_HANDLER + e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);

    }

}
