package com.database.dto.payment.viettel.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPriceAllDTO {
    private String SENDER_PROVINCE;
    private String SENDER_DISTRICT;
    private String RECEIVER_PROVINCE;
    private String RECEIVER_DISTRICT;
    private String PRODUCT_TYPE;
    private Float PRODUCT_WEIGHT;
    private Double PRODUCT_PRICE;
    private Double MONEY_COLLECTION;
    private Integer TYPE;
}