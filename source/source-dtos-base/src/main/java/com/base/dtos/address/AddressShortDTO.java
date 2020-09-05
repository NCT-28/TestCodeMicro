package com.base.dtos.address;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AddressShortDTO {
    private UUID country;
    private UUID cityOrProvince;
    private UUID district;
    private UUID wardOrCommune;

    public AddressShortDTO(UUID country, UUID cityOrProvince, UUID district, UUID wardOrCommune) {
        this.country = country;
        this.cityOrProvince = cityOrProvince;
        this.district = district;
        this.wardOrCommune = wardOrCommune;
    }
}