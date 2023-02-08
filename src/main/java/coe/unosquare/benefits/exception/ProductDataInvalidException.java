package coe.unosquare.benefits.exception;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Product Data Invalid Exception
 */
public class ProductDataInvalidException extends Exception{
    String message;
    List<ValidationError> listOfErrors = new ArrayList<>();


    /**
     * ProductDataInvalidException
     *
     * @param message the message for the exception
     */
    public ProductDataInvalidException(String message) {
        super(message);
        this.message = message;
    }


    /**
     * ProductDataInvalidException
     *
     * @param message the message
     * @param validationErrorList the list of errors
     */
    public ProductDataInvalidException(String message, List<ValidationError> validationErrorList) {
        super(message);
        this.message = message;
        this.listOfErrors = validationErrorList;
    }

    /**
     * getListOfErrors
     *
     * @return the list of errors
     */
    public List<ValidationError> getListOfErrors(){
        return this.listOfErrors;
    }
}