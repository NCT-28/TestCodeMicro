package com.spring.entity.base;

import com.base.source.models.INaturalIdEntity;
import com.base.source.models.annotation.ReportFieldName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class NaturalIdEntity extends BaseEntity implements INaturalIdEntity<UUID> {

    @ReportFieldName("mã")
    @Column(unique = true, nullable = false)
    private String code;

    @Override
    public String toString() {
        return "NaturalIdEntity{" +
                "code='" + code + '\'' +
                '}';
    }
}