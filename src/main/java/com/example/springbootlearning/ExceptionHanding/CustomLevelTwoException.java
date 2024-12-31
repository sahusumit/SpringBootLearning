package com.example.springbootlearning.ExceptionHanding;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */


@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid request")
public class CustomLevelTwoException extends RuntimeException{
    public CustomLevelTwoException(String message){
        super(message);
    }
}
