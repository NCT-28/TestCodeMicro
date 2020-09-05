package com.spring.entity.base;

import com.base.source.models.IPrefixIdentifierEntity;
import com.base.source.models.annotation.ReportFieldName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class PrefixIdentifierEntity extends IdentifierEntity implements IPrefixIdentifierEntity<UUID> {
    @ReportFieldName("tiền tố")
    @Column(length = 100, nullable = false)
    private String prefix;
}