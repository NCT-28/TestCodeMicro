package com.database.dto.payment;

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
public class ConfigsConnectShippingDTO extends BaseEntityDTO {
    private UUID shippingPartnerUuid;
    private String urlConnect;
    private String urlListInventory;
    private String urlGetListService;
    private String urlGetListServiceExtra;
    private String urlCreateOrder;
    private String urlUpdateOrder;
    private String urlGetPrice;
    private String urlGetPriceAll;
}