package com.base.dtos.others;

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
public class UuidNameDTO extends BaseEntityDTO {
    private String name;
    public UuidNameDTO(UUID uuid, String name) {
        super(uuid);
        this.name = name;
    }
}