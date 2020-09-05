package com.spring.entity.base.converts;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringToListConvert implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        if (strings == null) {
            return null;
        }
        return String.join(",", strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return Arrays.stream(s.split(",")).collect(Collectors.toList());
    }
}