package com.base.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecureHashDTO {
    private String urlPay;
    private String version;
    private String secureHashType;
    private String secureHash;
    private String data;
}