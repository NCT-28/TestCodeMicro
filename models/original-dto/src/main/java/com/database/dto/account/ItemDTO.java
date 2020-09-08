package com.database.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    // ======================== [Thông tin sản phẩm] ========================
    private String auctionsOnlineCode;
    private String sku;
    private String tenSanPham;
    private UUID productPortfolioUuid;
    private UUID donViTinhUuid;
    // ======================== [Thông tin giảm giá] ========================
    private String maGiamGia;
    private Double giamGia;
    // ======================== [Thông tin đặt mua] ========================
    private Float totalVAT;
    private Double donGia;
    private Integer quantify;
    private String context;

    // ======================== [Init] ========================
    public ItemDTO(String auctionsOnlineCode, String sku, UUID productPortfolioUuid, Double donGia) {
        this.auctionsOnlineCode = auctionsOnlineCode;
        this.sku = sku;
        this.productPortfolioUuid = productPortfolioUuid;
        this.donGia = donGia;
    }
}