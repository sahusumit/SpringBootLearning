package com.example.springbootlearning.ExceptionHanding;

import java.util.Date;

public class ErrorResponse {
    private Date timestamp;
    private String message;
    private int code;

    public ErrorResponse(Date timestamp, String message, int code){
        this.timestamp = timestamp;
        this.message = message;
        this.code = code;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
