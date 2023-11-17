package nl.novi.techiteasy.exceptions;

public class NameToLongException extends RuntimeException {
    public NameToLongException() {
        super();
    }

    public NameToLongException(String message) {
        super(message);
    }
}