package pl.polsl.view;

import java.util.Scanner;
/**
 * Class used to read user input from command line.
 * 
 * @author Adam Musiał
 * @version 1.0
 * 
 */
public class InputReader {
    /**
     * Scanner used to take input.
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * Reads input and saves this to String.
     * @return the line of input text
     */
    public String readString()
    {
        String line = scanner.nextLine();
        return line;
    }
    /**
     * Reads input and saves this to integer if it possible.
     * @return the saved input as integer
     */
    public final int readInteger() {
        int number = 0;
        try {
            number = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("String cannot be converted to integer!");
        }
        return number;
    }
}
