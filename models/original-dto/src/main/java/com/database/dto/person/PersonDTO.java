package com.database.dto.person;

import com.base.dtos.jpa.BaseEntityDTO;
import com.base.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonDTO extends BaseEntityDTO {
    private String hoTen;
    private Gender gioiTinh;
    private String dinhDanhCaNhan;
    private String noiCap;
    private String soDienThoai;
    private UUID cityUuid;
    private UUID districtUuid;
    private String fullAddress;
    private UUID wardUuid;
    private String idUpload;
    private String moTaHinhAnh;
}
