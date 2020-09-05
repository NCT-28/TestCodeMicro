package com.source.base.controller;

import org.springframework.http.ResponseEntity;

public interface BaseFindByIdController<T, ID> {

    String _find = "/findById";
    String _summary = "Tìm theo ID";
    String _id_description = "ID cần tìm";

    ResponseEntity<?> get(ID id, String select) throws Exception;
}