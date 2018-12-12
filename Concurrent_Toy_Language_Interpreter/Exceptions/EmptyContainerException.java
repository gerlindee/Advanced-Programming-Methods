package Exceptions;

public class EmptyContainerException extends Exception {
    private String message;

    public EmptyContainerException() {
        this.message = "Empty container!";
    }

    public String getMessage() {
        return this.message;
    }
}