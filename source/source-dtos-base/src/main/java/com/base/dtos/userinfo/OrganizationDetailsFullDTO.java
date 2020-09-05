package com.base.dtos.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrganizationDetailsFullDTO extends OrganizationDetailsShortDTO {
    private String tenGiaoDich;
    private String tenVietTat;
    private String tenTiengAnh;
    private Date ngayCapGiayPhep;
    private Date ngayBatDauHoatDong;
    private String fax;
    private String website;
}