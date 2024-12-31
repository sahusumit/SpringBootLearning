package com.example.springbootlearning.ExceptionHanding.controllers;

import com.example.springbootlearning.ExceptionHanding.CustomException;
import com.example.springbootlearning.ExceptionHanding.CustomLevelTwoException;
import com.example.springbootlearning.ExceptionHanding.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/")
public class userController {


    /**
     * in this example we catch the exception and
     * we create the response entity object and pass the body and status and return it
     * and no exception handle resolved involved in this.
     * @return
     */
    @GetMapping(path = "/get-user")
    public ResponseEntity<?> getUser(){
        try {
            throw new CustomException(HttpStatus.BAD_REQUEST, "userId is missing");
        } catch (CustomException ex){
            // this is the pojo class for setting and sending the exception information in response entity
            ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), ex.getStatus().value());
            return  new ResponseEntity<>(errorResponse, ex.getStatus());
        }catch (Exception ex){
            ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            return  new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * In this example we just simply throw the exception. we do not create the ResponseEntity so somebody
     * has to create for us. for that spring exception framework comes to picture. and exception resolver invoked
     * and comes into picture.
     * Exception pass through each resolver
     * if anyone able to handle the exception then it simply set the status and message in HTTP response and pass into the sequence.
     * and finally pass through the defaultErrorAttribute and return then response
     * If exception not handled by any of the resolver the defaultErrorAttribute set default status:500 and internal server message with other info
     * like timestamp etc. and send the response
     * @return
     */
    @GetMapping(path = "/get-user")
    public ResponseEntity<?> getUser1(){
            throw new CustomException(HttpStatus.BAD_REQUEST, "userId is missing");

    }

    @GetMapping(path = "/get-user2")
    public ResponseEntity<?> getUserExample2(){
        throw new CustomException(HttpStatus.BAD_REQUEST, "userId is missing");

    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex){
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), ex.getStatus().value());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex){
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
    * if we want to send the same response and HTTPS status the we can use in same method like this
    */
    @ExceptionHandler({HttpServerErrorException.class, NullPointerException.class })
    public ResponseEntity<Object> handleCustomIllegalArgumentException(IllegalArgumentException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * if we not want to return the responseEntity in the @ExceptionHandler method.
     * then DefaultErrorAttribute to create the responseEntity and handle it.
     *
     * @ExceptionHandler method can takes 3 parameters that can be in any order
     *  HttpServletResponse, HttpServletRequest and Exception
     *
     */

    @ExceptionHandler(CustomException.class)
    public void handleCustomIllegalArgumentException(HttpServletResponse response, CustomException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }


    /**
     * This goes to resolver in sequence.
     * 1- ExcpetionHandlerExceptionResolver- it is not found any method annotated with @ExceptionHandler with the CustomLevelTwoException class
     * is it is not resolved or handled by ExcpetionHandlerExceptionResolver so it goes to
     * level 2 - ResponseStatusExceptionResolver - as this is uncaught exception by and this exception class is annotated
     * with @ResponseStatus if it is  then it handled by ResponseStatusExceptionResolver with status or message mention
     * in @ResponseStatus
     *
     * NOTE: message present in @ResponseStatus annotation has more priority than the message passed in exception itself.
     * @return
     */
    @GetMapping(path = "/get-user3")
    public ResponseEntity<?> getUserExample3(){
        throw new CustomLevelTwoException("UserId is missing");
    }
}
