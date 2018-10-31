package Exceptions;

public class Exception1 extends Exception {
    private String message;

    public Exception1(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
