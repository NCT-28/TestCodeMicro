package com.base.source.models;

public interface IPictureEntity<T> extends IBaseEntity<T> {

    String getIdUpload();

    void setIdUpload(String idUpload);

    String getPicture();

    void setPicture(String picture);

    Boolean getExternal();

    void setExternal(Boolean external);

    String getDescription();

    void setDescription(String description);
}