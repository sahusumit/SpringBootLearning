package com.example.springbootlearning.ExceptionHanding;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is the custom exception class annotated with @ResponseStatus
 * which means if any CustomLevelTwoException uncaught by the ExceptionHandlerExceptionResolver then
 *
 * it goes to ResponseStatusExceptionResolver it check the CustomLevelTwoException annotated with @ResponseStatus
 * then it will resolve this exception and send the response
 *
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid request")
public class CustomLevelTwoException extends RuntimeException{
    public CustomLevelTwoException(String message){
        super(message);
    }
}
