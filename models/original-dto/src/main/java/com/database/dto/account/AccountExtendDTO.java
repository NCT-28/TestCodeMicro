package com.database.dto.account;

import com.base.dtos.AccountInfoDTO;
import com.base.dtos.GrantedDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountExtendDTO extends AccountInfoDTO {

    private Boolean enabled;

    private AccountDetailsDTO accountDetails;

    private Set<GrantedDTO> accountAuthorities;
}