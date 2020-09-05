package com.spring.entity.base;

import com.base.source.models.IDescriptionEntity;
import com.base.source.models.annotation.ReportFieldName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class DescriptionEntity extends IdentifierEntity implements IDescriptionEntity<UUID> {

    @ReportFieldName("Mô tả")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Override
    public String toString() {
        return "DescriptionEntity{" +
                "description='" + description + '\'' +
                '}';
    }
}