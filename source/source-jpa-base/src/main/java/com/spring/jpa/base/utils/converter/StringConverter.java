package com.spring.jpa.base.utils.converter;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class StringConverter {

    public static String convertArrayToDelimiter(String[] attribute) {
        return String.join(",", attribute);
    }

    public static <T> String convertDelimiterToSet(Set<String> attribute) {
        return Arrays.stream(attribute.toArray()).map(Object::toString).collect(Collectors.joining(","));
    }

    public static <T> Set<String> convertDelimiterToSet(String data) {
        return Arrays.stream(data.split(",")).collect(Collectors.toSet());
    }
}