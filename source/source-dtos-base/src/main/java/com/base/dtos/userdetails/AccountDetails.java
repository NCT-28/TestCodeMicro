package com.base.dtos.userdetails;

import com.base.dtos.userinfo.AccountInfo;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountDetails extends AccountInfo implements UserDetails {

    @Setter
    private String password;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
