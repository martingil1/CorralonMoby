package com.practice.mobydigital.controllers;

import com.practice.mobydigital.exceptions.ProductAlreadyExistsException;
import com.practice.mobydigital.exceptions.ProductNotExistsException;
import com.practice.mobydigital.models.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotExistsException.class)
    public ErrorResponse handleFlightNotExistHandling(ProductNotExistsException ex) {

        return ErrorResponse.fromRunTimeException(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ErrorResponse handleCodeAlreadyRegistered(ProductAlreadyExistsException ex) {

        return ErrorResponse.fromRunTimeException(ex);
    }

}
