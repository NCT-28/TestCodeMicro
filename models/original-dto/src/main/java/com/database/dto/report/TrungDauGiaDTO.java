package com.database.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class TrungDauGiaDTO {
    private UUID taiSanUuid;
    private String nguoiTrung;
    private String tenLo;
    private Double dienTich;
    private Double giaKhoiDiem;
    private Double giaTrungDau;
    private Double datTruoc;
    private Integer nguoiThamGia;

}
