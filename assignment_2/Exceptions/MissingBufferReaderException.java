package Exceptions;

public class MissingBufferReaderException extends Exception {
    private String message;

    public MissingBufferReaderException(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }
}
