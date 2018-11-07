package pl.polsl.caesarcipher;

import pl.polsl.controller.EncryptionCoordinator;
/**
 * Main class, uses controller to execute Caesar Cipher encryption/decryption algorithm. 
 * 
 * @author Adam Musiał
 * @version 1.0
 * 
 */
public class CaesarCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        EncryptionCoordinator encryptionCoordinator = new EncryptionCoordinator();
        encryptionCoordinator.start(args);
    }
}
