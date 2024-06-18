package ua.learnukr.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.learnukr.exceptions.NoCoinsException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHolder {
    // Обробник для винятку NoSuchElementException
    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchElement(Exception ex){
        return ex.getMessage();
    }

    // Обробник для винятку NoSuchElementException
    @ExceptionHandler(NoCoinsException.class)
    public String noCoins(Exception ex){
        return ex.getMessage();
    }
}
