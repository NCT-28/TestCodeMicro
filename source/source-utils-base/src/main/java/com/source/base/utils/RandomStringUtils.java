package com.source.base.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.UUID;

@UtilityClass
public class RandomStringUtils {

    String ALPHA_NUMERIC_STRING = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
    String ALPHA_STRING = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;

    public String generatingRandomAlphabeticString() {
        return new Random().ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String generatingRandomAlphanumericString() {
        return new Random().ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String generatingRandomStringBounded() {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            builder.append(ALPHA_NUMERIC_STRING.charAt((int) (Math.random() * ALPHA_NUMERIC_STRING.length())));
        }
        return builder.toString();
    }

    public String randomAlphaWithCompanyCode(int count, String code) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            builder.append(ALPHA_STRING.charAt((int) (Math.random() * ALPHA_STRING.length())));
        }
        return String.join("_", builder, code);
    }

    public String randomUUID(int count) {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String randomAlphaNumericUUID(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            builder.append(ALPHA_NUMERIC_STRING.charAt((int) (Math.random() * ALPHA_NUMERIC_STRING.length())));
        }
        return builder.toString() + UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String randomAlphaNumericWithUUID(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            builder.append(ALPHA_NUMERIC_STRING.charAt((int) (Math.random() * ALPHA_NUMERIC_STRING.length())));
        }
        return builder.toString() + UUID.randomUUID().toString();
    }
}