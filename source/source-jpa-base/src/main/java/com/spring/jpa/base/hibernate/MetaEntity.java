package com.spring.jpa.base.hibernate;

import lombok.Data;

import javax.persistence.metamodel.Attribute.PersistentAttributeType;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.Type;
import java.util.Set;

@Data
public class MetaEntity {
    private String name;
    private Type idType;
    private Bindable.BindableType bindableType;
    private Type.PersistenceType persistenceType;
    private boolean hasSingleIdAttribute;
    private boolean hasVersionAttribute;
    private Set<Attribute> attributes;
    @Data
    public static class Attribute {
        private boolean isAssociation;
        private boolean isCollection;
        private java.lang.reflect.Member member;
        private PersistentAttributeType persistentAttributeType;
    }
}