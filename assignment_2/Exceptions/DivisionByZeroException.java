package Exceptions;

public class DivisionByZeroException extends Exception {
    private String message;

    public DivisionByZeroException() {
        this.message = "Invalid arithmetric expression! Division by zero not possible!";
    }

    public String getMessage() {
        return this.message;
    }
}
