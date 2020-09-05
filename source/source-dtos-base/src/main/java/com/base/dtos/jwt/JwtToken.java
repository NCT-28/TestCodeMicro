package com.base.dtos.jwt;

import com.base.dtos.GrantedDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class JwtToken implements Authentication {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String identifier;
    private final List<?> roles;

    public JwtToken(String identifier, List<?> roles) {
        this.identifier = identifier;
        this.roles = roles;
    }

    public UUID getId() {
        return UUID.fromString(identifier);
    }

    public List<?> getRoles() {
        return roles;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public String getName() {
        return this.identifier;
    }

    @Override
    public Object getPrincipal() {
        return this.identifier;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(x -> new GrantedDTO(x.toString())).collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return "JWT.IO allows you to decode, verify and generate JWT.";
    }

    @Override
    public Object getDetails() {
        return "JSON Web Tokens are an open, industry standard RFC 7519 method for representing claims securely between two parties.";
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
    }
}