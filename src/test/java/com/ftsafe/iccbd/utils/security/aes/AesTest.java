package com.ftsafe.iccbd.utils.security.aes;

import com.ftsafe.iccbd.utils.security.exception.CryptoException;
import junit.framework.TestCase;

import java.io.File;

/**
 * Created by qingyuan on 17-4-25.
 */
public class AesTest extends TestCase {
    public void testEncrypt() throws Exception {
        String key = "Mary has one cat";
        File inputFile = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Hydrangeas.jpg");
        File encryptedFile = new File("document.encrypted.jpg");
        File decryptedFile = new File("document.decrypted.jpg");

        try {
            Aes.encrypt(key, inputFile, encryptedFile);
            Aes.decrypt(key, encryptedFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void testDecrypt() throws Exception {

    }
}