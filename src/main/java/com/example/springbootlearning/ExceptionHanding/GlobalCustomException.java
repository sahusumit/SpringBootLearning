package com.example.springbootlearning.ExceptionHanding;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * If multiple controller has the same type of excpetions then the same handling
 * might be required in all controller which tends to code duplication.
 * To dealt with code duplication issue we can handle in global level class
 * annotated with @ControllerAdvice.
 *
 *
 * Any exception happens priority goes to @ExceptionHandler method defined in controller it self
 * if it will found in thr controller
 * if not found any relevant @ExceptionHandler it check for the globalException Class
 * which is annotated with @ControllerAdvice.
 */
@ControllerAdvice
public class GlobalCustomException {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex){
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleCustomException(IllegalArgumentException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }


}
