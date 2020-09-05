package com.base.dtos.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NaturalIdEntityDTO extends BaseEntityDTO {
    private String code;
}