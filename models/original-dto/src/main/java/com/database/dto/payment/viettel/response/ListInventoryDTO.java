package com.database.dto.payment.viettel.response;

import lombok.Data;

import java.util.List;

@Data
public class ListInventoryDTO {
    private Integer status;
    private Boolean error;
    private String message;
    private List<DataResult> data;

    @Data
    public static class DataResult {
        private Long groupaddressId; // Mã kho
        private Long cusId; // Mã khách hàng
        private String name; // Tên khách hàng
        private String phone; // Số điện thoại

        private String address; // Địa chỉ
        private Integer provinceId; // Mã tỉnh/thành phố
        private Integer districtId; // Mã quận/huyện
        private Integer wardsId; // Mã xã/phường
    }
}
//        {
//            "status": 200,
//                "error": false,
//                "message": "OK",
//                "data": [
//            {
//                "groupaddressId": 7199571,
//                    "cusId": 6737965,
//                    "name": "Nguyễn Đình Tạo",
//                    "phone": "0962503466",
//                    "address": "212/6 Hoàng Văn Thụ",
//                    "provinceId": 40,
//                    "districtId": 464,
//                    "wardsId": 8544
//            }
//  ]
//        }