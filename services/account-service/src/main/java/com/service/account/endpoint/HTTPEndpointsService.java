package com.service.account.endpoint;

import java.util.List;

public interface HTTPEndpointsService<T> {

    Boolean checkExist(T dto);

    T findEndpoints(T dto);

    List<String> findAllEndpointByServerName(String serverName);

    boolean deleteWithPatternAndServerName(String pattern, String serverName);
}