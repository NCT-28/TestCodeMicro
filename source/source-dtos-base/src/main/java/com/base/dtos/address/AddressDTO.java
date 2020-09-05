package com.base.dtos.address;

import com.base.dtos.jpa.BaseEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddressDTO extends BaseEntityDTO {
    private UUID country;
    private UUID cityOrProvince;
    private UUID district;
    private UUID wardOrCommune;
    private String fullAddress;

    public AddressDTO(UUID country, UUID cityOrProvince, UUID district, UUID wardOrCommune) {
        this.country = country;
        this.cityOrProvince = cityOrProvince;
        this.district = district;
        this.wardOrCommune = wardOrCommune;
    }
}