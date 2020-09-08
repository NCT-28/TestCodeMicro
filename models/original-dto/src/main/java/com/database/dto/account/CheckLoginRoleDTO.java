package com.database.dto.account;

import com.base.dtos.userinfo.AuthorityInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CheckLoginRoleDTO {

    private UUID id;

    private String username;

    private String password;

    private Set<AuthorityInfo> authorities;

    public CheckLoginRoleDTO(UUID id, String username, String password) {
        this(id, username, password, null);
    }

    public CheckLoginRoleDTO(UUID id, String username, String password, Set<AuthorityInfo> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
}