package com.spring.entity.base;

import com.base.source.models.IIdentifierEntity;
import com.base.source.models.annotation.ReportFieldName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class IdentifierEntity extends NaturalIdEntity implements IIdentifierEntity<UUID> {

    @ReportFieldName("Tên")
    @Column(nullable = false)
    private String name;

    @Override
    public String toString() {
        return "IdentifierEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}