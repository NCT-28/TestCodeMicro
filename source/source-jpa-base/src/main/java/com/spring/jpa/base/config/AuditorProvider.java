package com.spring.jpa.base.config;

import com.base.dtos.jwt.JwtToken;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorProvider implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (a == null || !a.isAuthenticated() || a instanceof AnonymousAuthenticationToken) {
            return Optional.of("account-public");
        }
        if (a instanceof JwtToken) {
            return Optional.ofNullable(a.getPrincipal().toString());
        }
        return Optional.of("anonymous");
    }
}