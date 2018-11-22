package pl.polsl.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.polsl.annotations.UnitTest;

/**
 * Tests written for OutputFileSaver's methods.
 *
 * @author Adam Musiał
 * @version 1.0
 *
 */
public class OutputFileSaverTest {

    OutputFileSaver outputFileSaver;

    /**
     * Test setup.
     */
    @Before
    public void setup() {
        outputFileSaver = new OutputFileSaver();
    }

    /**
     * Test of saveToFile method, of class OutputFileSaver.
     */
    @UnitTest
    @Test
    public void testSaveToFile() {

        try {
            outputFileSaver.saveToFile("resources/Test1Save.txt", "Ala ma kota.");
            outputFileSaver.saveToFile("resources/Test2Save.txt", "");
            outputFileSaver.saveToFile("resources/Test2Save.txt", null);
            fail("An exception should be thrown when the text is null.");
        } catch (FileNotFoundException e) {

        } catch (NullPointerException | IOException e) {

        }

        try {
            outputFileSaver.saveToFile(null, null);
            fail("An exception should be thrown when the file path is null and text is null.");
        } catch (FileNotFoundException e) {

        } catch (NullPointerException | IOException e) {

        }

        try {
            outputFileSaver.saveToFile(null, "Ala ma kota.");
            fail("An exception should be thrown when the file path is null.");
        } catch (FileNotFoundException e) {

        } catch (NullPointerException | IOException e) {

        }

    }

}
