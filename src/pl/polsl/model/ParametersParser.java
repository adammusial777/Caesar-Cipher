package pl.polsl.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pl.polsl.exceptions.IncorrectParameterException;
/**
 * Class used to parse parameters from input data.
 * 
 * @author Adam Musiał
 * @version 1.0
 * 
 */
public class ParametersParser {
    /**
     * List of parameters.
     */
    private List<String> parameters = new ArrayList<>();
    /**
     * Variable describes if program is executes with parameters.
     */
    private boolean areParameters = false;
    /**
     * The file access path.
     */
    private String filepath = new String();
    /**
     * Cipher key - alphabet shift.
     */
    private int key = 0;
    /**
     * Variable describes that default is choosen encrypt text operation.
     */
    private boolean encryption = true;
    /**
     * Parses and sets parameters with program is called.
     * @throws IncorrectParameterException if parameters are incorrect
     */
    public void parse() throws IncorrectParameterException {
        if(checkEmptyParameters()) {
            checkTooManyParameters();
            checkSwitches();
            checkForParametersAfterSwitches();
            setEncryption();
            setInputParameters();
        }
    }
    /**
     * Checks if the program is called with parameters and set areParameters variable.
     * @return the information about parameters exist
     */
    private boolean checkEmptyParameters() {
        areParameters = !parameters.isEmpty();
        return areParameters;
    }
    /**
     * Checks if user not enter to many parameters.
     * @throws IncorrectParameterException if user enter more than 5 parameters
     */
    private void checkTooManyParameters() throws IncorrectParameterException {
        if(parameters.size() > 5) {
            throw new IncorrectParameterException("Too many parameters!");
        }
    }
    /**
     * Checks if user not enter correct switches.
     * @throws IncorrectParameterException if switches not match
     */
    private void checkSwitches() throws IncorrectParameterException {
        if(!(parameters.contains("-f") && parameters.contains("-k") && (parameters.contains("-e") || parameters.contains("-d")))) {
            throw new IncorrectParameterException("Missing one or more switches!");      
        }
    }
    /**
     * Checks if user not enter correct parameters after switches.
     * @throws IncorrectParameterException if parameters after switches not match
     */
    private void checkForParametersAfterSwitches() throws IncorrectParameterException {
        int indexOfFilepath = parameters.indexOf("-f") + 1;
        int indexOfKey = parameters.indexOf("-k") + 1;
        if(!parameters.get(indexOfFilepath).matches(".+\\.txt")) {
            throw new IncorrectParameterException("Incorrect filepath!");
        }
        if(!parameters.get(indexOfKey).matches("\\d+")) {
            throw new IncorrectParameterException("Incorrect key!");
        }
    }
    /**
     * Sets encription/decryption switch from parameters. 
     */
    private void setEncryption() {
        if(parameters.contains("-e")) {
            encryption = true;
        } else if(parameters.contains("-d")) {
            encryption = false;
        }
    }
    /**
     * Sets filepath and key from the parameters.
     */
    private void setInputParameters() {
        int indexOfFilepath = parameters.indexOf("-f") + 1;
        int indexOfKey = parameters.indexOf("-k") + 1;
        filepath = parameters.get(indexOfFilepath);
        key = Integer.parseInt(parameters.get(indexOfKey));
    }
    /**
     * Sets parameters array.
     * @param param the parameters array to set
     */
    public void setParameters(String[] param) {
        parameters = Arrays.asList(param);
    }
    /**
     * Gets variable describes if are parameters.
     * @return are parameters switch
     */
    public boolean getAreParameters() {
        return areParameters;
    }
    /**
     * Gets filepath.
     * @return filepath
     */
    public String getFilepath() {
        return filepath;
    }
    /**
     * Gets key.
     * @return key
     */
    public int getKey() {
        return key;
    }
    /**
     * Gets variable describes if default encrypt operation is realize.
     * @return encryption switch
     */
    public boolean getEncryption() {
        return encryption;
    }
}
