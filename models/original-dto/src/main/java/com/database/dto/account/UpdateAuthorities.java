package com.database.dto.account;

import com.base.dtos.jpa.BaseEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAuthorities {
    // id tài khoản
    private UUID uuid;
    // danh sách vai trò cần được cập nhật
    private List<BaseEntityDTO> authorities;
}