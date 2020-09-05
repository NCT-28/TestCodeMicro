package com.base.dtos.userinfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class AccountRequired {

    private String username;

    private String password;

    private String rePassword;
}