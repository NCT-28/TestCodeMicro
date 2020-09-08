package com.database.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CartItemDTO extends ItemDTO {
    private UUID uuid;
    private Date ngayThem;
    private Date ngayCapNhat;
}