package com.database.dto.payment.viettel.response;

import lombok.Data;

@Data
public class LoginResultDTO {
    private Integer status;
    private Boolean error;
    private String message;
    private DataResult data;

    @Data
    public static class DataResult {
        private Integer userId;
        private String token;
        private String partner;
        private String phone;
        private Long expired;
        private String source;
    }
}