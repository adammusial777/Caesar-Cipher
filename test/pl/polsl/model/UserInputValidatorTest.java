package pl.polsl.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.polsl.annotations.UnitTest;
import pl.polsl.model.exceptions.IncorrectInputParameterException;

/**
 * Tests written for UserInputValidator's methods.
 *
 * @author Adam Musiał
 * @version 1.0
 *
 */
public class UserInputValidatorTest {

    UserInputValidator userInputValidator;

    /**
     * Test setup.
     */
    @Before
    public void setup() {
        userInputValidator = new UserInputValidator();
    }

    /**
     * Test of validateEncryptionParameter method, of class UserInputValidator.
     */
    @UnitTest
    @Test
    public void testValidateEncryptionParameter() {
        try {
            userInputValidator.validateEncryptionParameter("e");
            userInputValidator.validateEncryptionParameter("d");
            userInputValidator.validateEncryptionParameter("");
            fail("An exception should be thrown when the parameter is empty.");
        } catch (NullPointerException | IncorrectInputParameterException e) {

        }

        try {
            userInputValidator.validateEncryptionParameter(null);
            fail("An exception should be thrown when the parameter is null.");
        } catch (NullPointerException | IncorrectInputParameterException e) {

        }
    }

    /**
     * Test of validateInputText method, of class UserInputValidator.
     */
    @UnitTest
    @Test
    public void testValidateInputText() {
        try {
            userInputValidator.validateInputText("Simple text.");
            userInputValidator.validateInputText("");
            userInputValidator.validateInputText("©ipher");
            fail("An exception should be thrown when the text is invalid.");
        } catch (NullPointerException | IncorrectInputParameterException e) {

        }

        try {
            userInputValidator.validateInputText("VieƜ");
            fail("An exception should be thrown when the text is invalid.");
        } catch (NullPointerException | IncorrectInputParameterException e) {

        }

        try {
            userInputValidator.validateInputText("Ɠeorƍ");
            fail("An exception should be thrown when the text is invalid.");
        } catch (NullPointerException | IncorrectInputParameterException e) {

        }

        try {
            userInputValidator.validateInputText(null);
            fail("An exception should be thrown when the text is null.");
        } catch (NullPointerException | IncorrectInputParameterException e) {

        }
    }
}
