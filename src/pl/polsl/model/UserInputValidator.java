package pl.polsl.model;

import pl.polsl.model.exceptions.IncorrectInputParameterException;
import static pl.polsl.utility.CharactersConstants.FIRST_OF_USED_CHARACTERS;
import static pl.polsl.utility.CharactersConstants.LAST_OF_USED_CHARACTERS;
/**
 * Class used to valid input data.
 * 
 * @author Adam Musiał
 * @version 1.1
 * 
 */
public class UserInputValidator {
    /**
     * Validates parameter responsible for encrypt or decrypt text entered manually.
     * @param parameter the user input text
     * @throws IncorrectInputParameterException if parameter is different than "e" and "d"
     */
    public void validateEncryptionParameter(String parameter) throws IncorrectInputParameterException {
        if(!parameter.equals("e") && !parameter.equals("d")) {
            throw new IncorrectInputParameterException("Wrong encryption parameter was entered!");
        }
    }
    /**
     * Validates input text entered manually.
     * @param text the user input text
     * @throws IncorrectInputParameterException if any character in String is not in used characters
     */
    public void validateInputText(String text) throws IncorrectInputParameterException {
        for(int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if(character < FIRST_OF_USED_CHARACTERS || character > LAST_OF_USED_CHARACTERS) {
                throw new IncorrectInputParameterException(Character.toString(character));
            }
        }
    }
    /**
     * Validates key entered manually.
     * @param key the user input text
     * @throws NumberFormatException if key parsing to integer was unsuccessful
     */
    @Deprecated
    public void validateInputKey(String key) throws NumberFormatException {
        try { 
            Integer.parseInt(key); 
        } catch(NumberFormatException e) { 
            throw new NumberFormatException("Wrong key was entered!");
        }
    }
}
