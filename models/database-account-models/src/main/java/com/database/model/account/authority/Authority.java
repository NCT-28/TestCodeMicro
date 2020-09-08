package com.database.model.account.authority;

import com.base.source.models.annotation.ReportFieldJoinName;
import com.base.source.models.annotation.ReportFieldName;
import com.base.source.models.annotation.ReportTableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.original.tag.models.account.TagAuthority;
import com.original.tag.models.partner.TagCompany;

import com.spring.entity.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table
@NoArgsConstructor
@ReportTableName(value = TagAuthority.name, name = TagAuthority.description)
public class Authority extends BaseEntity implements GrantedAuthority {

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority(GrantedAuthority authority) {
        this.authority = authority.getAuthority();
    }

    @ReportFieldName("vai trò")
    @Column(length = 150, nullable = false, unique = true)
    private String authority;

    @ReportFieldName("tên vai trò")
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @ReportFieldName("Là vai trò hệ thống")
    private Boolean isRoot;

    @ReportFieldName("cho phép đăng nhập Web quản trị")
    @Column(length = 150, nullable = false)
    private Boolean enableAdmin;

    @ReportFieldName("cho phép đăng nhập Web khách hàng")
    @Column(length = 150, nullable = false)
    private Boolean enableClient;

    @ReportFieldName("cho phép đăng nhập Web đối tác")
    @Column(length = 150, nullable = false)
    private Boolean enablePartner;

    @ReportFieldName("vai trò dành cho đối tác")
    @Column(length = 150, nullable = false)
    private Boolean forPartner;

    @Column
    @ReportFieldName(value = "id " + TagCompany.description, description = "Id liên kết với bảng " + TagCompany.name)
    private UUID companyUUID;

    @ReportFieldJoinName(TagAuthority.description)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Authority parent;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "parent")
    private Set<Authority> children;

    @JsonIgnore
    @OneToMany(targetEntity = AccountAuthority.class, cascade = CascadeType.ALL, mappedBy = "authority")
    private Set<AccountAuthority> accountAuthorities;

    @JsonIgnore
    @OneToMany(targetEntity = AuthorityHTTPEndpoints.class, cascade = CascadeType.ALL, mappedBy = "authority")
    private Set<AuthorityHTTPEndpoints> authorityHTTPEndpoints;

    @JsonIgnore
    @OneToMany(targetEntity = AuthorityMenuAdministration.class, cascade = CascadeType.ALL, mappedBy = "authority")
    private Set<AuthorityMenuAdministration> authorityMenuAdministrations;

    @JsonIgnore
    @OneToMany(targetEntity = AuthorityMenuPartner.class, cascade = CascadeType.ALL, mappedBy = "authority")
    private Set<AuthorityMenuPartner> authorityMenuPartners;

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return authority.equals(authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authority='" + authority + '\'' +
                ", name='" + name + '\'' +
                ", isRoot=" + isRoot +
                ", enableAdmin=" + enableAdmin +
                ", enableClient=" + enableClient +
                ", enablePartner=" + enablePartner +
                ", companyUUID=" + companyUUID +
                '}';
    }
}