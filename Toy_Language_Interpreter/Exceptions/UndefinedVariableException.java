package Exceptions;

public class UndefinedVariableException extends Exception {
    private String message;

    public UndefinedVariableException() {
        this.message = "Undefined variable!";
    }

    public String getMessage() {
        return this.message;
    }
}