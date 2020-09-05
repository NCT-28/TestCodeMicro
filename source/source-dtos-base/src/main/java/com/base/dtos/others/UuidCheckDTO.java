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
public class UuidCheckDTO extends CheckDTO {
    private UUID uuid;
    public UuidCheckDTO(UUID uuid, Boolean check) {
        super(check);
        this.uuid = uuid;
    }
}