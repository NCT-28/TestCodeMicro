package com.database.dto.account;

import com.base.dtos.userinfo.PeopleDetailsFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountDetailsFullDTO extends PeopleDetailsFullDTO {
    private String about;
}