package Exceptions;

public class EmptyContainerException extends Exception {
    private String message;

    public EmptyContainerException() {
        this.message = "Emtpy container!";
    }

    public String getMessage() {
        return this.message;
    }
}