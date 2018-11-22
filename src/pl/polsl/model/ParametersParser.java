package pl.polsl.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pl.polsl.model.exceptions.IncorrectInputParameterException;

/**
 * Class used to parse parameters from input data.
 *
 * @author Adam Musiał
 * @version 1.1
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
     *
     * @throws IncorrectInputParameterException if parameters are incorrect
     */
    public void parse() throws IncorrectInputParameterException {
        if (checkEmptyParameters()) {
            checkTooManyParameters();
            checkSwitches();
            checkForParametersAfterSwitches();
            setEncryption();
            setInputParameters();
        }
    }

    /**
     * Checks if the program is called with parameters and set areParameters
     * variable.
     *
     * @return the information about parameters exist
     */
    private boolean checkEmptyParameters() {
        areParameters = !parameters.isEmpty();
        return areParameters;
    }

    /**
     * Checks if user enter too many parameters.
     *
     * @throws IncorrectInputParameterException if user enter more than 5
     * parameters
     */
    private void checkTooManyParameters() throws IncorrectInputParameterException {
        int size = 0;
        for (String p : parameters) {
            if (!p.equals("")) {
                size++;
            }
        }
        if (size > 5) {
            throw new IncorrectInputParameterException("Too many parameters!");
        }
    }

    /**
     * Checks if user not enter correct switches.
     *
     * @throws IncorrectInputParameterException if switches not match
     */
    private void checkSwitches() throws IncorrectInputParameterException {
        if (!(parameters.contains("-f") && parameters.contains("-k") && (parameters.contains("-e") || parameters.contains("-d")))) {
            throw new IncorrectInputParameterException("Missing one or more switches!");
        }
    }

    /**
     * Checks if user not enter correct parameters after switches.
     *
     * @throws IncorrectInputParameterException if parameters after switches not
     * match
     */
    private void checkForParametersAfterSwitches() throws IncorrectInputParameterException {
        int indexOfFilepath = parameters.indexOf("-f") + 1;
        int indexOfKey = parameters.indexOf("-k") + 1;
        if (!parameters.get(indexOfFilepath).matches(".+\\.txt")) {
            throw new IncorrectInputParameterException("Incorrect filepath!");
        }
        if (!parameters.get(indexOfKey).matches("\\d+")) {
            throw new IncorrectInputParameterException("Incorrect key!");
        }
    }

    /**
     * Sets encription/decryption switch from parameters.
     */
    private void setEncryption() {
        if (parameters.contains("-e")) {
            encryption = true;
        } else if (parameters.contains("-d")) {
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
     *
     * @param param the parameters array to set
     */
    public void setParameters(String[] param) {
        parameters = Arrays.asList(param);
    }

    /**
     * Gets variable describes if are parameters.
     *
     * @return are parameters switch
     */
    public boolean getAreParameters() {
        return areParameters;
    }

    /**
     * Gets filepath.
     *
     * @return filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Gets key.
     *
     * @return key
     */
    public int getKey() {
        return key;
    }

    /**
     * Gets variable describes if default encrypt operation is realize.
     *
     * @return encryption switch
     */
    public boolean getEncryption() {
        return encryption;
    }
}
