package com.base.dtos.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UuidNamePrefixDTO extends UuidNameDTO {
    private String prefix;

    public UuidNamePrefixDTO(UUID uuid, String name, String prefix) {
        super(uuid, name);
        this.prefix = prefix;
    }
}