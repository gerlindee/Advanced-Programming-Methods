package Exceptions;

public class MissingBufferReaderException extends Exception {
    private String message;

    public MissingBufferReaderException() {
        this.message = "Buffer reader does not exist!";
    }

    public String getMessage() {
        return this.message;
    }
}