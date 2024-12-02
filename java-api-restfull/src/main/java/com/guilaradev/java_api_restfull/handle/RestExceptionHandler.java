package com.guilaradev.java_api_restfull.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.guilaradev.java_api_restfull.model.Error.ErrorMessage;
import com.guilaradev.java_api_restfull.model.Exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResponseNotFoundException(ResourceNotFoundException expt){
        ErrorMessage err = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value() ,expt.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

}
