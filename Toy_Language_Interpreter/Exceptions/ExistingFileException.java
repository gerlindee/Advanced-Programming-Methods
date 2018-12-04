package Exceptions;

public class ExistingFileException extends Exception {
    private String message;

    public ExistingFileException() {
        this.message = "File already exists!";
    }

    public String getMessage() {
        return this.message;
    }

}