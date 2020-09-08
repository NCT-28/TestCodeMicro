package com.database.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallBackDTO {
    // ================================
    private UUID paymentPartnerUuid;
    // ================================
    private UUID companyUuid;
    private String token;
    // ================================
    private String amount;
    private String bankCode;
    private String bankTranNo;
    private String cardType;
    private String orderInfo;
    private String payDate;
    private String responseCode;
    private String tmnCode;
    private String transactionNo;
    private String txnRef;
    private String secureHashType;
    private String secureHash;
}