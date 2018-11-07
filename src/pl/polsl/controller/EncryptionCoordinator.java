package pl.polsl.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import pl.polsl.model.OutputFileSaver;
import pl.polsl.model.InputFileReader;
import pl.polsl.model.ParametersParser;
import pl.polsl.model.CaesarCryptographer;
import pl.polsl.model.UserInputValidator;
import pl.polsl.view.OutputPresenter;
import pl.polsl.view.InputReader;
import pl.polsl.exceptions.IncorrectParameterException;
import pl.polsl.exceptions.IncorrectCharacterException;
/**
 * Controller class, manages all processes of application. 
 * 
 * @author Adam Musiał
 * @version 1.0
 * 
 */
public class EncryptionCoordinator {
    /**
    * Paser of parameters from command line.
    */
    private final ParametersParser parametersParser = new ParametersParser();
    /**
     * Reader of input from command line.
     */
    private final InputReader inputReader = new InputReader();
    /**
     * Presenter of output to the user, through the command line.
     */
    private final OutputPresenter outputPresenter = new OutputPresenter();
    /**
     * Caesar Cipher cryptographer.
     */
    private final CaesarCryptographer caesarCryptographer = new CaesarCryptographer();
    /**
     * Data reader of input file.
     */
    private final InputFileReader inputFileReader = new InputFileReader();
    /**
     * Data saver of output file.
     */
    private final OutputFileSaver outputFileSaver = new OutputFileSaver();
    /**
     * Validator of user input.
    */
    private final UserInputValidator userInputChecker = new UserInputValidator();
    /**
     * Loads text from file to String variable.
     * @param filepath the file access path 
     * @return content of text from the filepath
     */
    private String readInputData(String filepath) {
        String textContent = "";
        try{
            textContent = inputFileReader.readFromFile(filepath);
        }
        catch(FileNotFoundException e) {
            OutputPresenter.presentOutput(e.getMessage());
        }
        catch(IOException e) { 
            OutputPresenter.presentOutput(e.getMessage());
        }
        return textContent;
    }
    /**
     * Saves text to the file. Check state (encryption or decryption) and append
     * at the end of filename information about operation was execute to the file.
     * @param filepath the file access path 
     * @param text the text saved to the file
     * @param encription state inform encrypt (true) or decrypt (false) operation
     * was execute on the file
     */
    private void saveToFile(String filepath, String text, boolean encription) {
        String pattern = ".txt";
        if (encription) {
            filepath = filepath.replace(pattern, "Encrypted" + pattern);
        }
        else {
            filepath = filepath.replace(pattern, "Decrypted" + pattern );
        }
        try{
            outputFileSaver.saveToFile(filepath, text);
        }
        catch(IOException e) {
            OutputPresenter.presentOutput(e.getMessage());
        }
    }
    /**
     * Asks user for all input data from the command line, 
     * when user want to encrypt/decrypt text entered manually.
     */
    private void askForInputData() {
        char encryptionParameter = askForEncryptionParameter();
        askForInputText(encryptionParameter);
        askForInputKey();
    }
    /**
     * Asks user for operation he will do on the text from the command line, 
     * when user entered text manually.
     * @return 'e' or 'd' character symbolize encrypt or decrypt operation to execute
     */
    private char askForEncryptionParameter() {
        OutputPresenter.presentOutput("Enter \"e\" for encrypt or \"d\" for decrypt Your text:");
        String encryptionParameter = "";
        try { 
            encryptionParameter = inputReader.readString();
            userInputChecker.validateEncryptionParameter(encryptionParameter);
        }
        catch (IncorrectParameterException e) {
            OutputPresenter.presentOutput(e.getMessage());
        }
        return encryptionParameter.charAt(0);
    }
    /**
     * Asks user for text he will encrypted/decrypted and executes selected operation
     * and validate it.
     * @param encryptionParameter inform which operation will be execute to the text
     */
    private void askForInputText(char encryptionParameter) {
        OutputPresenter.presentOutput("Enter Yout text:");
        String text = inputReader.readString();
        try {
            userInputChecker.validateInputText(text);
        }
        catch (IncorrectCharacterException e) {
            OutputPresenter.presentOutput(e.getMessage());
        }
        switch(encryptionParameter) {
            case 'e': 
                caesarCryptographer.setPlainText(text);
                break;
            case 'd':
                caesarCryptographer.setCipherText(text);
                break;
            default:
                break;
        }
    }
    /**
     * Asks user for cipher key and validates it.
     */
    private void askForInputKey() {
        OutputPresenter.presentOutput("Enter key:");
        caesarCryptographer.setCipherKey(inputReader.readInteger());
    }
    /**
     * Encrypts text given as a parameter. Validates text if contain incorrect character.
     * @param plainText the text on which encryption is executed
     * @param key the cipher alphabet shift 
     * @return text after encryption
     */
    public String encrypt(String plainText, int key) {
        String encryptedText = "";
        try {
            encryptedText = caesarCryptographer.encrypt(plainText, key);
        }
        catch (IncorrectCharacterException e) {
            OutputPresenter.presentOutput("Input text contain invalid character: " + e.getMessage() + "!");
        }
        return encryptedText;
    }
   /**
     * Decrypts text given as a parameter. Validates text if contain incorrect character.
     * @param encryptedText the text on which encryption is executed
     * @param key the cipher alphabet shift 
     * @return text after decryption
     */
    private String decrypt(String encryptedText, int key) {
        String plainText = "";
        try {
            plainText = caesarCryptographer.decrypt(encryptedText, key);
        }
        catch (IncorrectCharacterException e) {
            OutputPresenter.presentOutput("Input text contain invalid character: " + e.getMessage() + "!");
        }
        return plainText;
    }
    /**
     * Parses command line input parameters.
     */
    private void parse() {
        try{
            parametersParser.parse();
        } 
        catch(IncorrectParameterException e) {
            OutputPresenter.presentOutput(e.getMessage());
        }
    }
    /**
     * Main method coordinates program start with switches option or manually 
     * data enter encryption/decryption process.
     * @param args command line arguments
     */
    public void start(String[] args) {
        parametersParser.setParameters(args);
        parse();
        String text;
        if(parametersParser.getAreParameters()) {
            text = readInputData(parametersParser.getFilepath());
            if(parametersParser.getEncryption()) {
                text = encrypt(text, parametersParser.getKey());
            } 
            else {
                text = decrypt(text, parametersParser.getKey());
            }
            saveToFile(parametersParser.getFilepath(), text, parametersParser.getEncryption());
        }
        else {
            askForInputData();
            if(!caesarCryptographer.getPlainText().equals(""))
            {
                text = encrypt(caesarCryptographer.getPlainText(), caesarCryptographer.getCipherKey());
                OutputPresenter.presentOutput("Your encrypted text is: " + text);
            }
            else if(!caesarCryptographer.getCipherText().equals("")) {
                text = decrypt(caesarCryptographer.getCipherText(), caesarCryptographer.getCipherKey());
                OutputPresenter.presentOutput("Your decrypted text is: " + text);
            }
        }
    }
}
