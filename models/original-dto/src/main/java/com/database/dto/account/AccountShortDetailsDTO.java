package com.database.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountShortDetailsDTO {

    private String name;

    private String email;

    private String phoneNumber;
}