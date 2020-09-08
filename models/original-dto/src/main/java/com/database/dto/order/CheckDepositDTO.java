package com.database.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckDepositDTO {

    private UUID accountUuid;
    private Double deposits;
    private String auctionsOnlineCode;

    public CheckDepositDTO(Double deposits) {
        this.deposits = deposits;
    }

    public CheckDepositDTO(UUID accountUuid, String auctionsOnlineCode) {
        this.accountUuid = accountUuid;
        this.auctionsOnlineCode = auctionsOnlineCode;
    }
}