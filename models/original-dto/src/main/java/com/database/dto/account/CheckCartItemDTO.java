package com.database.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckCartItemDTO {
    private String auctionsOnlineCode;
    private String sku;
    private UUID productPortfolioUuid;
}
