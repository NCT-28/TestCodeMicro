package com.base.dtos.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PeopleDetailsFullDTO extends PeopleDetailsShortDTO {
    private Date dateOfSupply;
    private String locationProvided;
}