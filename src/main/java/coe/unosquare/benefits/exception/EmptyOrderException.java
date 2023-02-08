package coe.unosquare.benefits.exception;

public class EmptyOrderException extends Exception{
    String message;

    public EmptyOrderException(String message) {
        super(message);
        this.message = message;
    }
}
