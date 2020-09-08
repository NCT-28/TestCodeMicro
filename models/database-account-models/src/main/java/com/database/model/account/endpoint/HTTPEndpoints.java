package com.database.model.account.endpoint;

import com.base.dtos.http.IEndpoints;
import com.base.enums.HttpMethod;
import com.database.model.account.authority.AuthorityHTTPEndpoints;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.original.tag.models.endpoint.TagHTTPEndpoints;
import com.base.source.models.annotation.ReportFieldJoinName;
import com.base.source.models.annotation.ReportFieldName;
import com.base.source.models.annotation.ReportTableName;
import com.spring.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table
@ReportTableName(value = TagHTTPEndpoints.name, name = TagHTTPEndpoints.description)
public class HTTPEndpoints extends BaseEntity implements IEndpoints {

    @Column(nullable = false)
    @ReportFieldName("công khai")
    private Boolean isPublic;

    @Column
    @ReportFieldName("yêu cầu xác thực")
    private Boolean isSecurity;

    @Column
    @ReportFieldName("tóm tắt")
    private String summary;

    @Column(nullable = false, unique = true)
    @ReportFieldName("đường dẫn")
    private String pattern;

    @Column(nullable = false)
    @ReportFieldName("Phương thức request")
    @Enumerated(EnumType.STRING)
    private HttpMethod method;

    @Column(nullable = false)
    @ReportFieldName("Tên nhóm")
    private String groupName;

    @Column(nullable = false)
    @ReportFieldName("Mô tả")
    private String groupDescription;

    @Column(nullable = false)
    @ReportFieldName("Tên Server")
    private String serverName;

    @JsonIgnore
    @OneToMany(targetEntity = AuthorityHTTPEndpoints.class, cascade = CascadeType.ALL, mappedBy = "httpEndpoints")
    private Set<AuthorityHTTPEndpoints> authorityHTTPEndpoints;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HTTPEndpoints that = (HTTPEndpoints) o;
        return pattern.equals(that.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern);
    }

    @Override
    public String toString() {
        return "HTTPEndpoints{" +
                "isPublic=" + isPublic +
                ", isSecurity=" + isSecurity +
                ", summary='" + summary + '\'' +
                ", pattern='" + pattern + '\'' +
                ", method=" + method +
                ", groupName='" + groupName + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
                ", serverName='" + serverName + '\'' +
                '}';
    }
}