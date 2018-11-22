package pl.polsl.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.polsl.annotations.UnitTest;
import pl.polsl.model.exceptions.IncorrectInputParameterException;

/**
 * Tests written for CaesarCryptographer's methods.
 *
 * @author Adam Musiał
 * @version 1.0
 *
 */
public class CaesarCryptographerTest {

    CaesarCryptographer caesarCryptographer;

    /**
     * Test setup.
     */
    @Before
    public void setup() {
        caesarCryptographer = new CaesarCryptographer();
    }

    /**
     * Test of encrypt method, of class CaesarCryptographer.
     */
    @UnitTest
    @Test
    public void testEncrypt() {

        try {
            assertEquals("Plain text is \"test\", key is 2. The result should be \"vguv\".", caesarCryptographer.encrypt("test", 2), "vguv");
            assertEquals("Plain text is \"test\", key is 1234. The result should be \"fqef\".", caesarCryptographer.encrypt("test", 1234), "fqef");
            assertEquals("Plain text is \"test\", key is -31. The result should be \"ozno\".", caesarCryptographer.encrypt("test", -31), "ozno");
            assertEquals("Plain text is \"test\", key is 0. The result should be \"test\".", caesarCryptographer.encrypt("test", 0), "test");
            assertEquals("Plain text is \"\", key is 3. The result should be \"\".", caesarCryptographer.encrypt("", 3), "");
            caesarCryptographer.encrypt("textØ", 3);
            fail("An exception should be thrown when the input is incorrect.");
        } catch (IncorrectInputParameterException e) {

        }

        try {
            caesarCryptographer.encrypt("ǢI", 15);
            fail("An exception should be thrown when the input is incorrect.");
        } catch (IncorrectInputParameterException e) {

        }

        try {
            caesarCryptographer.encrypt("schǾǾl", 67);
            fail("An exception should be thrown when the input is incorrect.");
        } catch (IncorrectInputParameterException e) {

        }
        try {
            caesarCryptographer.encrypt(null, 4);
        } catch (IncorrectInputParameterException | NullPointerException e) {

        }
    }

    /**
     * Test of decrypt method, of class CaesarCryptographer.
     */
    @UnitTest
    @Test
    public void testDecrypt() {

        try {
            assertEquals("Cipher text is \"student\", key is 5. The result should be \"nopyzio\".", caesarCryptographer.decrypt("student", 5), "nopyzio");
            assertEquals("Cipher text is \"student\", key is 2938. The result should be \"student\".", caesarCryptographer.decrypt("student", 2938), "student");
            assertEquals("Cipher text is \"student\", key is -101. The result should be \"pqrabkq\".", caesarCryptographer.decrypt("student", -101), "pqrabkq");
            assertEquals("Cipher text is \"student\", key is 0. The result should be \"student\".", caesarCryptographer.decrypt("student", 0), "student");
            assertEquals("Cipher text is \"\", key is 6. The result should be \"\".", caesarCryptographer.decrypt("", 6), "");
            caesarCryptographer.decrypt("pandaӜ", 77);
            fail("An exception should be thrown when the input is incorrect.");
        } catch (IncorrectInputParameterException e) {

        }

        try {
            caesarCryptographer.decrypt("Ψsomething", 9);
            fail("An exception should be thrown when the input is incorrect.");
        } catch (IncorrectInputParameterException e) {

        }

        try {
            caesarCryptographer.decrypt("gʘal", 32);
            fail("An exception should be thrown when the input is incorrect.");
        } catch (IncorrectInputParameterException e) {

        }

        try {
            caesarCryptographer.decrypt(null, 7);
        } catch (IncorrectInputParameterException | NullPointerException e) {

        }
    }
}
