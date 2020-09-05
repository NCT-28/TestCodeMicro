package com.base.dtos.userinfo;

import com.base.dtos.AccountInfoDTO;
import com.base.enums.AuthProvider;
import com.base.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountInfo extends AccountInfoDTO {

    private String sub;

    private AuthProvider provider;

    private String picture;

    // Custom
    private String firstName;
    private String middleName;
    private String lastName;

    private Date birthday;
    private Gender gender;

    private Set<AuthorityInfo> authorities;
}