package com.source.base.utils;

import lombok.experimental.UtilityClass;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SignatureException;
import java.util.Base64;

/**
 * @author ToanNC7
 */
@UtilityClass
public class GenerateHashKeyUtils {

    /**
     * Generate hash key with algorithm HmacSHA1
     *
     * @param secret secret key
     * @param data   content
     * @return key
     * @throws java.security.SignatureException failed to generate HMAC
     */
    public String generateHmacSHA512(String secret, String data) throws java.security.SignatureException {
        try {
            String HMAC_SHA512_ALGORITHM = "HmacSHA512";
            Mac m = Mac.getInstance(HMAC_SHA512_ALGORITHM);
            m.init(new SecretKeySpec(secret.getBytes(), HMAC_SHA512_ALGORITHM));
            return Base64.getEncoder().encodeToString(m.doFinal(data.getBytes()));
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
    }

    /**
     * Generate hash key with algorithm SHA-256
     *
     * @param data content
     * @return key
     * @throws java.security.SignatureException failed to generate SHA256
     */
    public String generateSHA256(String data) throws java.security.SignatureException {
        try {
            byte[] h = MessageDigest.getInstance("SHA-256").digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder s = new StringBuilder(2 * h.length);
            for (byte b : h) {
                s.append(String.format("%02x", b & 0xff));
            }
            return s.toString();
        } catch (Exception e) {
            throw new SignatureException("Failed to generate SHA-256 : " + e.getMessage());
        }
    }
}