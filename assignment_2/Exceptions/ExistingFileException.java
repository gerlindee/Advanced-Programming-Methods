package Exceptions;

public class ExistingFileException extends Exception {
    private String message;

    public ExistingFileException(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }

}
