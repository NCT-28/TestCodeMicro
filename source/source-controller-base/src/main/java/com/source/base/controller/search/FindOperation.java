package com.source.base.controller.search;


import com.base.enums.Ope;
import lombok.Data;

@Data
public class FindOperation {

    private String key;
    private Ope operation;
    private String value;
    private String sql;

    public FindOperation(String key, Ope operation, String value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.sql = null;
    }
}