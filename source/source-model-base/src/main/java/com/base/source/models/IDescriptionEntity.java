package com.base.source.models;

public interface IDescriptionEntity<T> extends IIdentifierEntity<T> {

    String getDescription();

    void setDescription(String description);
}
