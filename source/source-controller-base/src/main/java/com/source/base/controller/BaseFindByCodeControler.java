package com.source.base.controller;

import org.springframework.http.ResponseEntity;

public interface BaseFindByCodeControler<T> {
    String _find = "/findByCode";
    String _summary = "Tìm theo mã";
    String _id_description = "Mã cần tìm";
    ResponseEntity<?> getByCode(String code, String select);
}
