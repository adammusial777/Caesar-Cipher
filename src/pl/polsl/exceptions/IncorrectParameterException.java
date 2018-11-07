package pl.polsl.exceptions;
/**
 * Incorrect parameter exception class, extends Exception class, 
 * used to notify incorrect parameter input. 
 * 
 * @author Adam Musiał
 * @version 1.0
 * 
 */
public class IncorrectParameterException extends Exception {
    /**
     * Constructor.
     */
    public IncorrectParameterException() {}
    /**
     * Informs user what type of exception is handle.
     * @param message the text to inform user
     */
    public IncorrectParameterException(String message) {
        super(message);
    }
}
