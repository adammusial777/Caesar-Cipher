package pl.polsl.model;

import java.io.*;
/**
 * Class used to get and read data from file.
 * 
 * @author Adam Musiał
 * @version 1.1
 * 
 */
public class InputFileReader {
   /**
    * Reads text from file and write it to String.
    * @param filepath the file access path
    * @return text received from file
    * @throws FileNotFoundException if the file is not found 
    * @throws IOException if is a problem with IO file
    */
    public String readFromFile(String filepath) throws FileNotFoundException, IOException {
        String text = new String();
        BufferedReader reader = null;
        File file = new File(filepath);
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null) {
                text += line;
            }
        }
        catch(FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        }
        catch(NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
        catch(IOException e) {
            throw new IOException(e.getMessage());
        }
        finally {
            if(reader != null)
                reader.close();
        }
        return text;
    }
}
