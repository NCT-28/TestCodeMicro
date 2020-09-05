package com.base.dtos.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExDTO {
    private String p; // Endpoint cần kiểm tra
    private List<?> a; // Danh sách quyền
}