package com.spring.entity.base.converts;

import com.source.base.utils.AESEncryptionDecryptionUtils;

import javax.persistence.AttributeConverter;

public class StringToAESEncryptionConvert implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String originalInput) {
        if (originalInput == null) {
            return null;
        }
        return AESEncryptionDecryptionUtils.encrypt(originalInput);
    }

    @Override
    public String convertToEntityAttribute(String encodedString) {
        if (encodedString == null) {
            return null;
        }
        return AESEncryptionDecryptionUtils.decrypt(encodedString);
    }
}
