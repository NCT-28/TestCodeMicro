package com.database.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingInformationDTO {
    private UUID paymentMethodsUuid;
    private UUID paymentPartnerUuid;
    // ======================== [Thông tin thanh toán] ========================
    private String urlReturn;
    private String urlCancel;
    private String ipAddr;
    private String token;
}