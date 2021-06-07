package com.wcode.resume.exception;

public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String s) {
        super(s);
    }

    public ApiRequestException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
