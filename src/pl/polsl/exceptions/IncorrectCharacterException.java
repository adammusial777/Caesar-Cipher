package pl.polsl.exceptions;
/**
 * Incorrect character exception class, extends Exception class, 
 * used to notify incorrect character input. 
 * 
 * @author Adam Musiał
 * @version 1.0
 * 
 */
public class IncorrectCharacterException extends Exception {
    /**
     * Constructor.
     */
    public IncorrectCharacterException() {}
    /**
     * Informs user what type of exception is handle.
     * @param message the text to inform user
     */
    public IncorrectCharacterException(String message) {
        super(message);
    }
}
