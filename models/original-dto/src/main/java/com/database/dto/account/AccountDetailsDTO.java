package com.database.dto.account;

import com.base.dtos.userinfo.PeopleDetailsFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountDetailsDTO extends PeopleDetailsFullDTO {
    private UUID uuid;
    private String about;
}