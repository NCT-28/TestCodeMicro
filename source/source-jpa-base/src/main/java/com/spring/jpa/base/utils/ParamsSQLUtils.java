package com.spring.jpa.base.utils;

import com.base.enums.Ope;
import com.spring.jpa.base.repository.IRepository;
import com.spring.jpa.base.sql.ParamsSQL;
import lombok.experimental.UtilityClass;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@UtilityClass
public class ParamsSQLUtils {

    /**
     * Gán giá trị theo danh sách các Params
     *
     * @param q câu lệnh
     * @param p danh sách các param
     * @return q được gán các param
     */
    public Query setParameterForQuery(Query q, Iterable<ParamsSQL> p) {
        setParameter(q, p);
        return q;
    }

    /**
     * Gán giá trị theo danh sách các Params
     *
     * @param q   câu lệnh
     * @param p   danh sách các param
     * @param <T> loại model
     * @return q được gán các param
     */
    public <T> TypedQuery<T> setParameterForQuery(TypedQuery<T> q, Iterable<ParamsSQL> p) {
        setParameter(q, p);
        return q;
    }

    /**
     * Gán giá trị
     *
     * @param q câu lệnh
     * @param k danh sách các param
     */
    private void setParameter(Query q, Iterable<ParamsSQL> k) {
        for (ParamsSQL p : k) {
            if (p.getOperation() == Ope.in) {
                q.setParameter(p.getKey(), p.getValue());
                continue;
            }
            if (p.getType().equals("string")) {
                if (p.getOperation() == Ope.like) {
                    q.setParameter(p.getKey(), "%" + p.getValue() + "%");
                    continue;
                }
                q.setParameter(p.getKey(), p.getValue());
            }
            if (p.getType().equals("object")) {
                q.setParameter(p.getKey(), p.getValue());
                continue;
            }
            if (p.getType().equals(IRepository.baseIDName)) {
                q.setParameter(p.getKey(), UUID.fromString(p.getValue().toString()));
                continue;
            }
            if (p.getType().equals("bool")) {
                q.setParameter(p.getKey(), Boolean.parseBoolean(p.getValue().toString()));
                continue;
            }
            if (p.getType().equals("int")) {
                q.setParameter(p.getKey(), Integer.parseInt(p.getValue().toString()));
                continue;
            }
            if (p.getType().equals("long")) {
                q.setParameter(p.getKey(), Long.parseLong(p.getValue().toString()));
                continue;
            }
            if (p.getType().equals("float")) {
                q.setParameter(p.getKey(), Float.parseFloat(p.getValue().toString()));
                continue;
            }
            if (p.getType().equals("double")) {
                q.setParameter(p.getKey(), Double.parseDouble(p.getValue().toString()));
                continue;
            }
            if (p.getType().equals("date")) {
                OffsetDateTime offsetDate = OffsetDateTime.parse(p.getValue().toString());
                q.setParameter(p.getKey(), Date.from(offsetDate.toInstant()));
            }
        }
    }
}