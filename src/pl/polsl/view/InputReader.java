package pl.polsl.view;

import java.util.Scanner;

/**
 * Class used to read user input from command line.
 *
 * @author Adam Musiał
 * @version 1.1
 *
 */
public class InputReader {

    /**
     * Scanner used to take input.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Reads input and saves this to String.
     *
     * @return the line of input text
     */
    public String readString() {
        String line = scanner.nextLine();
        return line;
    }

    /**
     * Reads input and saves this to integer if it possible.
     *
     * @return the saved input as integer
     * @throws NumberFormatException if String cannot be converted to integer
     */
    public final int readInteger() throws NumberFormatException {
        int number = 0;
        try {
            number = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("String cannot be converted to integer!");
        }
        return number;
    }
    
    /**
     * Reads input and saves this to char.
     * 
     * @return the saves input as character
     */
    @Deprecated
    public char readChar() {
        char character = scanner.next().charAt(0);
        return character;
    }
}
