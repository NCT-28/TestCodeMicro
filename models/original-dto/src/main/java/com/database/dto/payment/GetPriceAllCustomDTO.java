package com.database.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPriceAllCustomDTO {
    private UUID senderProvince;
    private UUID senderDistrict;
    private UUID receiverProvince;
    private UUID receiverDistrict;
    private Double money;
    private Float weight;
}