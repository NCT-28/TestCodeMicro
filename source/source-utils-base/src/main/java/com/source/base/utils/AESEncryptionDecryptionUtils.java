package com.source.base.utils;


import lombok.experimental.UtilityClass;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author ToanNC7
 */
@UtilityClass
public class AESEncryptionDecryptionUtils {

    private final String ALGORITHM = "AES";

    public SecretKeySpec generateSecreteKey() throws Exception {
        try {
            return new SecretKeySpec(
                    Arrays.copyOf(MessageDigest.getInstance("SHA-1")
                            .digest("Wmz$C&F)J@NcRfU%jXn2r4u7x!".getBytes(StandardCharsets.UTF_8)), 16), ALGORITHM);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String encrypt(String str) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, generateSecreteKey());
            return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while Encrypting: " + e);
        }
        return null;
    }

    public String decrypt(String str) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, generateSecreteKey());
            return new String(cipher.doFinal(Base64.getDecoder().decode(str)));
        } catch (Exception e) {
            System.out.println("Error while Decrypting: " + e);
        }
        return null;
    }
}
