package com.database.model.account;

import com.base.source.models.annotation.ReportFieldName;
import com.base.source.models.annotation.ReportTableName;
import com.database.model.account.authority.AccountAuthority;
import com.database.model.account.avatar.Avatar;
import com.database.model.account.social.AccountSocialNetwork;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.original.tag.models.account.TagAccount;
import com.spring.entity.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Table
@Entity
@NoArgsConstructor
@ReportTableName(value = TagAccount.name, name = TagAccount.description)
public class Account extends BaseEntity {

    public Account(UUID uuid) {
        this.setUuid(uuid);
    }

    @Column(nullable = false, length = 50, unique = true)
    @ReportFieldName("tên đăng nhập")
    private String username;

    @JsonIgnore
    @ReportFieldName("mật khẩu")
    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false)
    @ReportFieldName("tên hiển thị")
    private String displayName;

    @Column(nullable = false)
    @ReportFieldName("khóa")
    private Boolean locked;

    @Column(nullable = false)
    @ReportFieldName("cho phép truy cập")
    private Boolean enabled;

    // Kiểm tra kích hoạt
    @Column(nullable = false)
    @ReportFieldName("đã kích hoạt")
    private Boolean activated;

    @OneToOne(targetEntity = AccountDetails.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
    private AccountDetails accountDetails;

    @JsonIgnore
    @OneToMany(targetEntity = AccountAuthority.class, cascade = CascadeType.ALL, mappedBy = "account")
    private Set<AccountAuthority> accountAuthorities;

    @JsonIgnore
    @OneToMany(targetEntity = AccountSocialNetwork.class, cascade = CascadeType.ALL, mappedBy = "account")
    private Set<AccountSocialNetwork> accountSocialNetworks;

    @JsonIgnore
    @OneToMany(targetEntity = Avatar.class, cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Avatar> avatars;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        var account = (Account) o;
        return username.equals(account.username) && password.equals(account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password);
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", displayName='" + displayName + '\'' +
                ", locked=" + locked +
                ", enabled=" + enabled +
                ", activated=" + activated +
                '}';
    }
}