package com.spring.entity.base;


import com.base.source.models.IBaseEntity;
import com.base.source.models.annotation.ReportFieldName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@Immutable
@MappedSuperclass
public abstract class BaseEntity extends AuditEntity<String> implements IBaseEntity<UUID> {

    @Id
    @GeneratedValue(generator = "UUID")
    @ReportFieldName(value = "id", primaryKey = true)
    @Column(updatable = false, nullable = false, unique = true)
    private UUID uuid;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "uuid=" + uuid +
                '}';
    }
}