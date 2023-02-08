package coe.unosquare.benefits.exception;


/**
 * The type ValidationError
 */
public class ValidationError {
    public String name;
    public String details;

    /**
     * Constructor to create a new instance of ValidationErrors.
     */
    public ValidationError(String name, String details) {
        this.name = name;
        this.details = details;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}