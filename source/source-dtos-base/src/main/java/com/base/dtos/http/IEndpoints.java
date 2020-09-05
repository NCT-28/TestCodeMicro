package com.base.dtos.http;

import com.base.enums.HttpMethod;

import java.util.UUID;

public interface IEndpoints {

    UUID getUuid();

    Boolean getIsPublic();

    Boolean getIsSecurity();

    String getSummary();

    String getPattern();

    HttpMethod getMethod();

    String getGroupName();

    String getGroupDescription();

    String getServerName();

    void setUuid(UUID uuid);

    void setIsPublic(Boolean aPublic);

    void setIsSecurity(Boolean security);

    void setSummary(String summary);

    void setPattern(String pattern);

    void setMethod(HttpMethod Method);

    void setGroupName(String groupName);

    void setGroupDescription(String groupDescription);

    void setServerName(String serverName);
}