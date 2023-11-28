package com.jason.ospchallenge.controller;

import com.jason.ospchallenge.controller.exception.InternalServerErrorException;
import com.jason.ospchallenge.controller.exception.InvalidRegionException;
import com.jason.ospchallenge.domain.data.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDto> internalServerErrorException(InternalServerErrorException ex) {
        ErrorDto errorDto = new ErrorDto("500", ex.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidRegionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> dateParseException(InvalidRegionException ex) {
        ErrorDto errorDto = new ErrorDto("400", ex.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
