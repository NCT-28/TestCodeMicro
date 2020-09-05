package com.spring.jpa.base.sql;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class WhereSQL {
    private List<ParamsSQL> paramsSQLS;
    private String sqlWhere;

    public WhereSQL(List<ParamsSQL> p, String s) {
        this.paramsSQLS = p;
        this.sqlWhere = s;
    }

    public void setParam(ParamsSQL ...p) {
        this.paramsSQLS.addAll(Arrays.asList(p));
    }
}