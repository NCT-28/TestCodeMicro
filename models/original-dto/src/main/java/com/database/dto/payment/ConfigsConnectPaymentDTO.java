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
public class ConfigsConnectPaymentDTO extends BaseEntityDTO {
    private UUID paymentPartnerUuid;
    private String urlPay;
    private String urlResult;
    private String urlCallBack;
    private String urlQueryTrans;
}