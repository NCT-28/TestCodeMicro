package com.spring.entity.base;

import com.base.source.models.IAuditEntity;
import com.base.source.models.annotation.ReportFieldName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity<T> implements IAuditEntity<T> {

    @JsonIgnore
    @CreatedBy
    @ReportFieldName("người tạo")
    @Column(nullable = false, updatable = false, length = 150)
    private T createdBy;

    @JsonIgnore
    @CreatedDate
    @ReportFieldName("ngày tạo")
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @JsonIgnore
    @ReportFieldName("người cập nhật")
    @Column(name = AuditConstant.updatedBy, length = 150)
    @LastModifiedBy
    private T updatedBy;

    @JsonIgnore
    @ReportFieldName("ngày cập nhật")
    @Column(name = AuditConstant.updatedDate)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Override
    public String toString() {
        return "AuditEntity{" +
                "createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate +
                '}';
    }
}