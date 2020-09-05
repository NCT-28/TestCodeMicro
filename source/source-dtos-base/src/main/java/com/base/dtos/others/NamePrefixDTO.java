package com.base.dtos.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NamePrefixDTO extends NameDTO {
    private String prefix;

    public NamePrefixDTO(String name, String prefix) {
        super(name);
        this.prefix = prefix;
    }
}