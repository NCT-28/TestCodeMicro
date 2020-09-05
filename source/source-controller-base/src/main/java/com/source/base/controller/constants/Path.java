package com.source.base.controller.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Path {

    private final String PREFIX_API_URL = "/api";
    public final String EXCHANGE_URL = "/exchange";

    // Not Check Token
    public final String PUBLIC_API_URL = PREFIX_API_URL + "/public";
    public final String ALLOWS_API_URL = PREFIX_API_URL + "/allows";

    // Check Token
    public final String AUTH_API_URL = PREFIX_API_URL + "/auth";
    public final String ADMIN_AUTH_API_URL = AUTH_API_URL + "/admin";
    public final String PARTNER_AUTH_API_URL = AUTH_API_URL + "/partner";
}