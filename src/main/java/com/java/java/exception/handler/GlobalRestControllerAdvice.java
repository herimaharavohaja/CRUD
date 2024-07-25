package com.java.java.exception.handler;

import com.java.java.exception.FileOperationException;
import com.java.java.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public AppError handleNotFoundException(NotFoundException ex) {
        return new AppError(ex.getMessage(), LocalDate.now(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(FileOperationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public AppError handleInternalServerError(NotFoundException ex) {
        return new AppError(ex.getMessage(), LocalDate.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
