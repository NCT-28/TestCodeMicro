package com.database.dto.payment.viettel.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPriceDTO {
    // 	Trọng lượng sản phẩm
    private Float PRODUCT_WEIGHT;
    // Giá trị sản phẩm
    private Double PRODUCT_PRICE;
    // Phí thu hộ (Số tiền hàng cần thu hộ - không bao gồm tiền cước)
    private Double MONEY_COLLECTION;
    // Dịch vụ (theo hợp đồng)
    private String ORDER_SERVICE;
    // 	Dịch vụ cộng thêm (theo hợp đồng). Dùng nhiều dịch vụ cộng thêm thì các dịch vụ cách nhau dấu “,”
    private String ORDER_SERVICE_ADD;
    // ID nơi gửi, nơi nhận
    private String SENDER_PROVINCE;
    private String SENDER_DISTRICT;
    private String RECEIVER_PROVINCE;
    private String RECEIVER_DISTRICT;
    // Loại sản phẩm:
    // + TH: Thư/ Envelope;
    // + HH: Hàng hóa/ Goods;
    private String PRODUCT_TYPE;
    // =1 : Trong nước/ inland
    // =0 : Quốc tế/ international
    private Integer NATIONAL_TYPE;
    // Chiều rộng/ width
    private Float PRODUCT_WIDTH;
    // 	Chiều cao/ height
    private Float PRODUCT_HEIGHT;
    // Chiều dài/ length
    private Float PRODUCT_LENGTH;
}