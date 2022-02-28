package com.example.setloggersb.exceptionHandeling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exceptions.EntityNotFoundException.class)
    public ResponseEntity<Object> exception(Exceptions.EntityNotFoundException exception){
        return ResponseEntity.status(404).body("Entity could not be found or does not exist: "+exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exceptions.NotImplemented.class)
    public ResponseEntity<Object> exception(Exceptions.NotImplemented exception){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented: "+exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exceptions.InputFieldNotFound.class)
    public ResponseEntity<Object> exception(Exceptions.InputFieldNotFound exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("InputField could not be found or does not exist: "+exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exceptions.NameNotUnique.class)
    public ResponseEntity<Object> exception(Exceptions.NameNotUnique exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exceptions.BadRequest.class)
    public ResponseEntity<Object> exception(Exceptions.BadRequest exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exceptions.CouldNotLogIn.class)
    public ResponseEntity<Object> exception(Exceptions.CouldNotLogIn exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
