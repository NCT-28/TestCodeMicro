package com.database.dto.account;

import com.base.dtos.jpa.BaseEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountDTO extends BaseEntityDTO {
    private String username;
    private String displayName;
    private Boolean locked;
    private Boolean enabled;
    private Boolean activated;
    private AccountDetailsDTO accountDetailsDTO;
    private List<AccountAddressDTO> accountAddressDTOS;
}