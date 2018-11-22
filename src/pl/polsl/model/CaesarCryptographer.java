package pl.polsl.model;

import pl.polsl.model.exceptions.IncorrectInputParameterException;
import pl.polsl.utility.CharactersConstants;
/**
 * Cryptographer class, is used to encrypt and decrypt text. 
 * 
 * @author Adam Musiał
 * @version 1.1
 * 
 */
public class CaesarCryptographer implements CharactersConstants {
    /**
    * Plain text in latin alphabet.
    */
    private String plainText = "";
    /**
    * Text encrypted with Caesar Cipher.
    */
    private String cipherText = "";
    /**
     * Caesar Cipher key.
     */
    private int cipherKey = 0;
    /**
     * Sets key if is bigger than number of latin alphabet or smaller than zero.
     * @param key the shift of the alphabet
     * @return seted key
     */
    private int setKey(int key) {
        if(key > NUMBER_OF_ALPHABET_CHARACTERS) {
            key = key % NUMBER_OF_ALPHABET_CHARACTERS;
        }
        else if(key < 0) {
            key = (key % NUMBER_OF_ALPHABET_CHARACTERS) + NUMBER_OF_ALPHABET_CHARACTERS;
        }
        return key;
    }
    /**
     * Encrypts text written in latin alphabet with Caesar Cipher algorithm.
     * @param text the text to encrypts
     * @param key the shift of alphabet
     * @return encrypted text
     * @throws IncorrectInputParameterException if any character in String is not in used characters
     */
    public String encrypt(String text, int key) throws IncorrectInputParameterException {
        cipherKey = setKey(key);
        cipherText = "";
        for(int i = 0; i < text.length(); i++) {
               char character = text.charAt(i);
           
            if(character < FIRST_OF_USED_CHARACTERS || character > LAST_OF_USED_CHARACTERS) {
                throw new IncorrectInputParameterException(Character.toString(character));
            }
            else {
                if(Character.isLetter(character)) {
                    if(Character.isLowerCase(character)) {
                        char encryptedCharacter = (char)(character + cipherKey);
                        if(encryptedCharacter > 'z') {
                            cipherText += (char)(character - (NUMBER_OF_ALPHABET_CHARACTERS - cipherKey));
                        }
                        else {
                            cipherText += encryptedCharacter;
                        }
                    }
                    else if(Character.isUpperCase(character)) {
                        char encryptedCharacter = (char)(character + cipherKey);
                        if(encryptedCharacter > 'Z') {
                            cipherText += (char)(character - (NUMBER_OF_ALPHABET_CHARACTERS - cipherKey));
                        }
                        else {
                            cipherText += encryptedCharacter;
                        }
                    }
                }
                else {
                    cipherText += character;
                }
           } 
       }
       return cipherText;
    }
   /**
    * Decrypts text shifted with Caesar Cipher algorithm to latin alphabet.
    * @param text the text to decrypts
    * @param key the shift of alphabet
    * @return decrypted text
    * @throws IncorrectInputParameterException if any character in String is not in used characters
    */
    public String decrypt(String text, int key) throws IncorrectInputParameterException {
        cipherKey = setKey(key);    
        plainText = "";
        for(int i = 0; i < text.length(); i++) {
           char character = text.charAt(i);
           if(character < FIRST_OF_USED_CHARACTERS || character > LAST_OF_USED_CHARACTERS) {
               throw new IncorrectInputParameterException(Character.toString(character));
           }
           else {
                if(Character.isLetter(character)) {
                    if(Character.isLowerCase(character)) {
                        char encryptedCharacter = (char)(character - cipherKey);
                        if(encryptedCharacter < 'a') {
                            plainText += (char)(character + (NUMBER_OF_ALPHABET_CHARACTERS - cipherKey));
                        }
                        else {
                            plainText += encryptedCharacter;
                        }
                    }
                    else if(Character.isUpperCase(character)) {
                        char encryptedCharacter = (char)(character - cipherKey);
                        if(encryptedCharacter < 'A') {
                            plainText += (char)(character + (NUMBER_OF_ALPHABET_CHARACTERS - cipherKey));
                        }
                        else {
                            plainText += encryptedCharacter;
                        }
                    }
                }
                else {
                    plainText += character;
                }
            }
        }
       return plainText;
    }
    /**
     * Gets plain text.
     * @return plain text
     */
    public String getPlainText() {
        return plainText;
    }
    /**
     * Gets cipher text.
     * @return cipher text
     */
    public String getCipherText() {
        return cipherText;
    }
    /**
     * Gets cipher key.
     * @return cipher key
     */
    public int getCipherKey() {
        return cipherKey;
    }
    /**
     * Sets plain text.
     * @param text the text to set
     */
    public void setPlainText(String text) {
        plainText = text;
    }
    /**
     * Sets cipher text.
     * @param text the text to set
     */
    public void setCipherText(String text) {
        cipherText = text;
    }
    /**
     * Sets cipher key.
     * @param key the key to set
     */
    public void setCipherKey(int key) {
        cipherKey = key;
    }
}