package com.database.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private UUID idCompany;
    private UUID idPaymentPartner;
    private String codeOrder;
    private Double amount;
    private String ipAddr;
    private String urlReturn;
    private String urlCancel;
}