package com.base.dtos.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResetPassword {
    private String newPassword;
    private String reNewPassword;
}