package Exceptions;

public class UndefinedOperationException extends Exception {
    private String message;

    public UndefinedOperationException() {
        this.message = "The operation you entered does not exist!";
    }

    public String getMessage() {
        return this.message;
    }
}