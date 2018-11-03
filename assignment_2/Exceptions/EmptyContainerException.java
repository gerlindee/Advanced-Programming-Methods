package Exceptions;

public class EmptyContainerException extends Exception {
    private String message;

    public EmptyContainerException(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }
}
