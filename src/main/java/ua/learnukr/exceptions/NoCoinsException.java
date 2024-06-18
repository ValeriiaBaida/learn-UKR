package ua.learnukr.exceptions;

public class NoCoinsException extends RuntimeException{
    public NoCoinsException() {
        super("There is not enough coins");
    }
}
