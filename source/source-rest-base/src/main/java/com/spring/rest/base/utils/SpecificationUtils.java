package com.spring.rest.base.utils;

import com.source.base.controller.search.FindOperation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SpecificationUtils {

    public <T> UrlSpecification<T> specification(FindOperation filterQuery) {
        if (filterQuery == null) {
            return null;
        }
        return new UrlSpecification<>(filterQuery);
    }
}