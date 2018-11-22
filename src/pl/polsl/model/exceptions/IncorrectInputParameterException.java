package pl.polsl.model.exceptions;
/**
 * Incorrect parameter exception class, extends Exception class, 
 * used to notify incorrect parameter input. 
 * 
 * @author Adam Musiał
 * @version 1.1
 * 
 */
public class IncorrectInputParameterException extends Exception {
    /**
     * Constructor.
     */
    public IncorrectInputParameterException() {}
    /**
     * Informs user what type of exception is handle.
     * @param message the text to inform user
     */
    public IncorrectInputParameterException(String message) {
        super(message);
    }
}
