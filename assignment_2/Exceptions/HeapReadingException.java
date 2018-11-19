package Exceptions;

public class HeapReadingException extends Exception {
    private String message;

    public HeapReadingException() {
        this.message = "Heap lccation not allocated. \n";
    }

    public String getMessage() {
        return this.message;
    }
}
