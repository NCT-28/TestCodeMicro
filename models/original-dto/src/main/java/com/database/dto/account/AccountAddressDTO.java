package com.database.dto.account;


import com.base.dtos.address.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountAddressDTO extends AddressDTO {
    private String name;
    private String nameComapny;
    private String phoneNumber;
    private UUID typeAddressUuid;
    private Boolean laMacDinh;
}