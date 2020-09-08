package com.database.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OnlineOrderDTO extends OrderDTO {
    private UUID shippingPartnerUuid;
    // ======================== [Nội dung thanh toán] ========================
    private BillingInformationDTO billingInformationDTO;
    private List<OnlineOrderItemDTO> orderItems;
}