package com.source.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class JsonUtils {

    public <T> T parseMap(Map<String, Object> objectMap, Class<T> tClass) {
        return new ObjectMapper().convertValue(objectMap, tClass);
    }

    public <T> T parseString(String json, Class<T> tClass) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, tClass);
    }

    public <T> Map<String, Object> convertPOJOtoMap(T object, Class<T> tClass) {
        return new ObjectMapper().convertValue(object, new TypeReference<>() {});
    }
}
