package com.base.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@AllArgsConstructor
public class GrantedDTO implements GrantedAuthority {

    private String a;

    public GrantedDTO(GrantedAuthority g) {
        this.a = g.getAuthority();
    }

    @Override
    public String getAuthority() {
        return a;
    }

    public void setAuthority(String authority) {
        this.a = authority;
    }
}