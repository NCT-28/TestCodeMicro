package com.database.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineOrderItemDTO {
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
}