package com.database.dto.account;

import com.base.dtos.address.AddressDTO;
import com.base.dtos.userinfo.AccountRequired;
import com.base.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountRegisterDTO extends AccountRequired {
    private String name;
    private String email;
    private Gender gender;
    private String phoneNumber;
    private Date birthday;
    private String IdentityCard;
    private AddressDTO addressDTO;
}