package pl.polsl.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.polsl.annotations.UnitTest;

/**
 * Tests written for InputFileReader's methods.
 *
 * @author Adam Musiał
 * @version 1.0
 *
 */
public class InputFileReaderTest {

    InputFileReader inputFileReader;

    /**
     * Test setup.
     */
    @Before
    public void setup() {
        inputFileReader = new InputFileReader();
    }

    /**
     * Test of readFromFile method, of class InputFileReader.
     */
    @UnitTest
    @Test
    public void testReadFromFile() {

        try {
            assertEquals("Reading from file named \"Test1Read.txt\", which includes \"Ala ma kota.\"", inputFileReader.readFromFile("resources/Test1.txt"), "Ala ma kota.");
            assertEquals("Reading from file named \"Test2Read.txt\", which includes nothing.", inputFileReader.readFromFile("resources/Test2.txt"), "");
            assertEquals(inputFileReader.readFromFile(null), null);
            fail("An exception should be thrown when the filepath is null.");
        } catch (FileNotFoundException e) {

        } catch (NullPointerException | IOException e) {

        }

        try {
            assertEquals(inputFileReader.readFromFile("resources/Test3Read.txt"), null);
            fail("An exception should be thrown when the file does not exist.");
        } catch (FileNotFoundException e) {

        } catch (NullPointerException | IOException e) {

        }
    }
}
