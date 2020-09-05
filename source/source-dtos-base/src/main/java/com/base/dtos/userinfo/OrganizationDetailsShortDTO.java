package com.base.dtos.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDetailsShortDTO {
    private String tenTochuc;
    private String maSoThue;
    private String soDienThoai;
    private String email;
}