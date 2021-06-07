package com.wcode.resume.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponse {

    private final String data;
    private final Boolean success;
    private final String timestamp;


    public ApiResponse(Boolean success, String data) {
        this.timestamp = Instant.now().toString();
        this.data = data;
        this.success = success;
    }

}
