package com.base.dtos.userinfo;

import com.base.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDetailsShortDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private Date birthday;
    private String identityCard;
}