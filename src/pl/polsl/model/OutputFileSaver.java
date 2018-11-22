package pl.polsl.model;

import java.io.*;
/**
 * Class used to save data to file.
 * 
 * @author Adam Musiał
 * @version 1.1
 * 
 */
public class OutputFileSaver {
   /**
    * Saves to file data from the String to file with the given path.
    * @param filepath the file access path
    * @param text the text to write to a file
    * @throws FileNotFoundException if is a file to save does not exist
    * @throws IOException if is a problem with IO file
    */
    public void saveToFile(String filepath, String text) throws FileNotFoundException, IOException {
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write(text);
        } catch(FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        } catch(NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        } catch(IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
