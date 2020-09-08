package com.database.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class OrderDTO {
    // ======================== [Số] ========================
    private UUID companyUuid;
    // ======================== [Thông tin thêm] ========================
    private String token;
    private UUID tienTeUuid;
    private String context;
    // ======================== [Thông tin người mua] ========================
    private String name;
    private String email;
    private String phoneNumber;
    private UUID countryUuid;
    private UUID cityOrProvinceUuid;
    private UUID districtUuid;
    private UUID wardOrCommuneUuid;
    private UUID streetUuid;
    private String fullAddress;
    // ======================== [Nội dung thanh toán] ========================
    private BillingInformationDTO billingInformationDTO;
}