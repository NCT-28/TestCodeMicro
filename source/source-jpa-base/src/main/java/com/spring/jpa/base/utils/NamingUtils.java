package com.spring.jpa.base.utils;

import lombok.experimental.UtilityClass;
import org.hibernate.boot.model.naming.Identifier;

import java.util.Locale;

@UtilityClass
public class NamingUtils {

    public Identifier apply(Identifier name) {
        if (name == null) {
            return null;
        }
        return new Identifier(toTableName(name.getText()), name.isQuoted());
    }

    public <T> String classToTableName(Class<T> t) {
        return toTableName(t.getSimpleName());
    }

    private String toTableName(String name) {
        StringBuilder b = new StringBuilder(name.replace('.', '_'));
        for (int i = 1; i < b.length() - 1; i++) {
            if (Character.isLowerCase(b.charAt(i - 1)) && Character.isUpperCase(b.charAt(i)) && Character.isLowerCase(b.charAt(i + 1))) {
                b.insert(i++, '_');
            }
        }
        return b.toString().toLowerCase(Locale.ROOT);
    }
}