package com.database.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetPriceCustomDTO extends GetPriceAllCustomDTO {
    private String service;
    private List<String> serviceAdd;
    private Boolean foreign;
    private float width;
    private float height;
    private float length;
}