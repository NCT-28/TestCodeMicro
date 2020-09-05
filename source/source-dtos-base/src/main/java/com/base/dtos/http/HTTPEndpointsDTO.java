package com.base.dtos.http;

import com.base.dtos.jpa.BaseEntityDTO;
import com.base.enums.HttpMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HTTPEndpointsDTO extends BaseEntityDTO implements IEndpoints {

    private Boolean isPublic;
    private Boolean isSecurity;
    private String summary;
    private String pattern;
    private HttpMethod method;
    private String groupName;
    private String groupDescription;
    private String serverName;

    public HTTPEndpointsDTO(String serverName) {
        this.serverName = serverName;
    }

    public HTTPEndpointsDTO(String pattern, String serverName) {
        this.pattern = pattern;
        this.serverName = serverName;
    }
}