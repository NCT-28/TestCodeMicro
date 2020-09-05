package com.base.dtos.address;

import com.base.dtos.others.UuidNameDTO;
import com.base.dtos.others.UuidNamePrefixDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDetailsDTO {
    private UuidNameDTO country;
    private UuidNamePrefixDTO cityOrProvince;
    private UuidNamePrefixDTO district;
    private UuidNamePrefixDTO wardOrCommune;
    private UuidNamePrefixDTO street;
    private String fullAddress;
}