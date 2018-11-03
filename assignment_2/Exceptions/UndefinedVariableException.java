package Exceptions;

public class UndefinedVariableException extends Exception {
    private String message;

    public UndefinedVariableException() {
        this.message = "UNDEFINED VARIABLE!";
    }

    public String getMessage() {
        return this.message;
    }
}
