package com.spring.jpa.base.utils.converter;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumConverter<E extends Enum<E>> implements AttributeConverter<Set<E>, String> {

    @Override
    public String convertToDatabaseColumn(Set<E> attribute) {
        return Arrays.stream(attribute.toArray()).map(Object::toString).collect(Collectors.joining(","));
    }

    @Override
    public Set<E> convertToEntityAttribute(String dbData) {
//        return Arrays.stream(dbData.split(",")).map(s -> RoleUser.valueOf(RoleClient.class, s)).collect(Collectors.toSet());
        return  null;
    }
}